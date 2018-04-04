package com.asiainfo.guava.primitive;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

/**
 * google primitive utils
 * 
 * @author       zq
 * @date         2018年4月1日  上午9:34:21
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class PrimitiveExample {

    /** 
     * TODO
     * 
     * @param args
     */
    public static void main(String[] args) {
        
        int[] iArray = {1, 2, 3, 4};
        // asList
        System.out.println("asList:" + Ints.asList(iArray));
        // max/min element
        System.out.println("max:" + Ints.max(iArray) + ", min:" + Ints.min(iArray));
        // reverse
        Ints.reverse(iArray);
        System.out.println("reverse:" + Ints.asList(iArray));
        // sort asc
        Arrays.sort(iArray);
        System.out.println("Arrays.sort:" + Ints.asList(iArray));
        // sort desc
        Ints.sortDescending(iArray);
        System.out.println("sortDescending:" + Ints.asList(iArray));
        // concat
        int[] iArr1 = {7, 5, 6};
        int[] nArr = Ints.concat(iArray, iArr1);
        System.out.println("concat:" + Ints.asList(nArr));
        // contains
        System.out.println("contains:" + Ints.contains(nArr, 6));
        // indexOf
        System.out.println("indexOf:" + Ints.indexOf(nArr, 6));
        System.out.println("indexOf:" + Ints.lastIndexOf(nArr, 6));
        System.out.println("indexOf:" + Ints.indexOf(nArr, iArr1));
        // join
        System.out.println("join:" + Ints.join(",", nArr));
        // toArray
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        System.out.println("toArray:" + Ints.join(" ", Ints.toArray(list)));
    }
}
