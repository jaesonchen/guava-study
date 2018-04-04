package com.asiainfo.guava.preconditions;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static com.google.common.base.Preconditions.checkPositionIndexes;
import static com.google.common.base.Preconditions.checkState;

/**
 * 不同于junit、java的assert，这里抛出RuntimeException
 * 
 * @author       zq
 * @date         2018年3月28日  下午4:48:15
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class PreconditionsExample {

    /** 
     * TODO
     * 
     * @param args
     */
    public static void main(String[] args) {

        // 检查boolean是否为真
        // 失败时抛出 IllegalArgumentException
        // Preconditions.checkArgument(boolean expression, String errMsg, Object... errMsgArgs)
        int i = 5;
        try {
            checkArgument(i > 6, "Argument was %s but expected nonnegative", i);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        
        // 检查value是否为null
        // 失败时抛出 NullPointerException
        // Preconditions.checkNotNull(T reference, String errMsg, Object... errMsgArgs)
        String s = null;
        try {
            checkNotNull(s, "String s shouldn't be null");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        // 检查对象的一些状态, 不依赖方法参数(相比checkArgument, 在某些情况下更有语义...)
        // 失败时抛出 IllegalStateException
        // Preconditions.checkState(boolean expression, String errMsg, Object... errMsgArgs)
        try {
            checkState(i > 6, "State shouldn't expected");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        
        // 检查index是否在合法范围[0, size)(不包含size)
        // 失败时抛出 IndexOutOfBoundsException
        //Preconditions.checkElementIndex(int index, int size, String desc)
        String s1 = "01234567";
        try {
            checkElementIndex(9, s1.length(), "index error...");
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        // 检查位置是否在合法范围[0, size](包含size)
        // 失败时抛出 IndexOutOfBoundsException
        //Preconditions.checkPositionIndex(int index, int size, String desc)
        try {
            checkPositionIndex(9, s1.length(), "index error...");
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        // 检查[start, end)是一个长度为size的集合合法的子集范围
        // 失败时抛出 IndexOutOfBoundsException
        //Preconditions.checkPositionIndexs(int start, int end, int size)
        try {
            checkPositionIndexes(-1, 4, s1.length());
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
