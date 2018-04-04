package com.asiainfo.guava.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.RoundingMode;

import com.google.common.math.IntMath;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年4月1日  下午7:27:20
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class MathExample {

    /** 
     * TODO
     * 
     * @param args
     */
    public static void main(String[] args) {

        // 溢出检查，快速失败
        try {
            IntMath.checkedAdd(Integer.MAX_VALUE, 1);
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }

        // 实数运算，java.math.RoundingMode
        // DOWN：向零方向舍入（去尾法）
        // UP：远离零方向舍入
        // FLOOR：向负无限大方向舍入
        // CEILING：向正无限大方向舍入
        // UNNECESSARY：不需要舍入，如果用此模式进行舍入，应直接抛出ArithmeticException
        // HALF_UP：向最近的整数舍入，其中x.5远离零方向舍入
        // HALF_DOWN：向最近的整数舍入，其中x.5向零方向舍入
        // HALF_EVEN：向最近的整数舍入，其中x.5向相邻的偶数舍入
        assertEquals(3, IntMath.sqrt(7, RoundingMode.CEILING));
        assertEquals(2, IntMath.log2(7, RoundingMode.DOWN));
        
        // 运算函数
        assertEquals(256, IntMath.pow(2, 8));
        assertEquals(120, IntMath.factorial(5));
        assertTrue(IntMath.isPowerOfTwo(128));
    }

}
