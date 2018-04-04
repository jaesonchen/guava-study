package com.asiainfo.guava.immutablecollections;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Lists;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年3月29日  下午4:07:52
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class ImmutableCollectionsExample {

    /** 
     * TODO
     * 
     * @param args
     */
    public static void main(String[] args) {

        jdkUnmodifiedList();
        create();
        asList();
        immutableCollections();
    }
    
    // jdk 持有原始引用时可以修改
    public static void jdkUnmodifiedList() {
        
        List<String> lists = Lists.newArrayList("aa", "bb", "cc");
        List<String> unmodifiedLists = Collections.unmodifiableList(lists);
        assertEquals(3, unmodifiedLists.size());
        lists.add("dd");
        assertEquals(4, unmodifiedLists.size());
    }
    
    // guava 不可变集合创建
    public static void create() {
        
        // copyOf
        List<Integer> list = Lists.newArrayList(1, 2, 3);
        ImmutableList<Integer> unmodifiedList = ImmutableList.copyOf(list);
        assertEquals(3, unmodifiedList.size());
        list.add(4);
        assertEquals(3, unmodifiedList.size());
        
        // of
        assertEquals(4, ImmutableList.of(1, 2, 3, 4).size());
        assertEquals(4, ImmutableSet.of(1, 2, 3, 4).size());
        assertEquals(4, ImmutableMap.of("aa", 1, "bb", 2, "cc", 3, "dd", 4).entrySet().size());
        assertEquals(4, (Object) ImmutableMap.of("aa", 1, "bb", 2, "cc", 3, "dd", 4).get("dd"));
        
        // builder
        ImmutableMap<Object, Object> map = ImmutableMap.builder()
                .put("aaa", 1)
                .put("bbb", 2)
                .put("ccc", 3)
                .build();
        assertEquals(3, map.size());
        assertEquals(1, map.get("aaa"));
        assertEquals(2, map.get("bbb"));
        assertEquals(3, map.get("ccc"));
    }

    // asList视图
    public static void asList() {
        
        ImmutableSortedSet<Integer> iset = ImmutableSortedSet.of(5, 2, 3, 4, 1);
        assertEquals(Lists.newArrayList(1, 2, 3, 4, 5), iset.asList());
    }

    // 不可变集合
    public static void immutableCollections() {
        
        assertEquals(5, ImmutableList.of(1, 2, 3, 4, 5).size());
        assertEquals(5, ImmutableSet.of(1, 2, 3, 4, 5).size());
        assertEquals(5, ImmutableSortedSet.of(1, 2, 3, 4, 5).size());
        assertEquals(3, ImmutableMap.of(1, 2, 3, 4, 5, 6).size());
        assertEquals(3, ImmutableSortedMap.of(1, 2, 3, 4, 5, 6).size());
        assertEquals(9, ImmutableMultiset.of(1, 1, 2, 2, 3, 3, 4, 5, 6).size());
        assertEquals(6, ImmutableMultiset.of(1, 1, 2, 2, 3, 3, 4, 5, 6).elementSet().size());
        assertEquals(2, ImmutableMultiset.of(1, 1, 2, 2, 3, 3, 4, 5, 6).count(1));
    }
}
