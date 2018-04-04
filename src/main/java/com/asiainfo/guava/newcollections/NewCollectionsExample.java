package com.asiainfo.guava.newcollections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Map.Entry;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.BoundType;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.RangeSet;
import com.google.common.collect.Sets;
import com.google.common.collect.SortedMultiset;
import com.google.common.collect.Table;
import com.google.common.collect.TreeMultiset;
import com.google.common.collect.TreeRangeMap;
import com.google.common.collect.TreeRangeSet;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年4月1日  下午7:36:08
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class NewCollectionsExample {

    /** 
     * TODO
     * 
     * @param args
     */
    public static void main(String[] args) {
        
        //1. Multiset<E> extends Collection<E> 
        // Multiset占据了List和Set之间的一个灰色地带：允许重复，但是不保证顺序。
        // jdk 对应的Multiset的实现: 
        // HashMultiset(HashMap)、TreeMultiSet(TreeMap)、LinkedHashMultiset(LinkedHashMap)、
        // ConcurrentHashMultiset(ConcurrentHashMap)、ImmutableMultiset(ImmutableMap)
        Multiset<String> set = HashMultiset.create(Lists.newArrayList("a", "b", "c", "a"));
        set.add("d");
        set.addAll(Lists.newArrayList("a", "b"));
        set.setCount("e", 2);
        assertEquals(3, set.count("a"));
        assertEquals(9, set.size());
        assertEquals(5, set.entrySet().size());
        assertEquals(5, set.elementSet().size());
        assertTrue(set.contains("a"));
        set.remove("a");
        assertEquals(2, set.count("a"));
        set.remove("a", 1);
        assertEquals(1, set.count("a"));
        // SortedMultiset是Multiset 接口的变种，它支持高效地获取指定范围的子集。
        // TreeMultiset实现了SortedMultiset接口。
        SortedMultiset<Integer> sortSet = TreeMultiset.<Integer>create(Lists.newArrayList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 150));
        assertEquals(9, sortSet.subMultiset(10, BoundType.CLOSED, 100, BoundType.OPEN).size()); // [10, 100)
        
        //2. MultiMap<K, V> 是把键映射到任意多个值的一种方式。
        // 一般情况下都会使用ListMultimap或SetMultimap接口，它们分别把键映射到List或Set。
        Multimap<String, String> map = ArrayListMultimap.create();
        map.put("a", "1");
        map.put("a", "2");
        map.put("b", "1");
        // Multimap.get(key)以集合形式返回键所对应的值视图, 即使没有任何对应的值，也会返回空集合。
        assertEquals(Lists.newArrayList("1", "2"), map.get("a"));
        assertTrue(null != map.get("c"));
        map.putAll("b", Lists.newArrayList("1", "2"));
        assertEquals(Lists.newArrayList("1", "1", "2"), map.get("b"));
        map.remove("b", "1");
        assertEquals(Lists.newArrayList("1", "2"), map.get("b"));
        map.replaceValues("b", Lists.newArrayList("3", "4"));
        assertEquals(Lists.newArrayList("3", "4"), map.get("b"));
        // Multimap<K, V>不是Map<K, Collection>，使用asMap()视图获取一个Map<K, Collection>
        for (Entry<String, Collection<String>> entry : map.asMap().entrySet()) {
            System.out.println(entry);
        }
        // 返回Multimap中所有”键-单个值映射”——包括重复键。
        for (Entry<String, String> entry : map.entries()) {
            System.out.println(entry);
        }
        
        //3. BiMap 提供了key和value双向关联的数据结构。
        BiMap<Integer, String> idAndName = HashBiMap.create();
        idAndName.put(1, "Rick");
        // inverse()反转BiMap<K, V>的键值映射, 反转的map不是新的map对象，它实现了一种视图关联，这样你对于反转后的map的所有操作都会影响原先的map对象
        BiMap<String, Integer> nameAndId = idAndName.inverse();
        assertEquals("Rick", idAndName.get(1));
        assertEquals(Integer.valueOf(1), nameAndId.get("Rick"));
        
        //4. Table 多个索引的数据结构
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("a", "x", 1);
        table.put("a", "y", 2);
        table.put("b", "x", 3);
        table.put("c", "z", 4);
        assertEquals(Sets.newHashSet("a", "b", "c"), table.rowKeySet());
        assertEquals(Sets.newHashSet("x", "y", "z"), table.columnKeySet());
        assertEquals(Integer.valueOf(2), table.row("a").get("y"));
        assertEquals(Integer.valueOf(3), table.column("x").get("b"));
        assertTrue(table.contains("a", "x"));
        assertEquals(2, table.rowMap().get("a").size());
        assertEquals(2, table.columnMap().get("x").size());
        
        //5. RangeSet
        RangeSet<Integer> rs = TreeRangeSet.create();
        rs.add(Range.closed(1, 3));
        rs.add(Range.closed(2, 4));
        assertEquals(1, rs.asRanges().size());
        assertTrue(rs.asRanges().contains(Range.closed(1, 4)));
        
        //6. RangeMap 和RangeSet不同，RangeMap不会合并相邻的映射
        RangeMap<Integer, String> rm = TreeRangeMap.create();
        rm.put(Range.closed(1, 3), "a");
        rm.put(Range.closed(2, 4), "b");
        rm.put(Range.closed(3, 5), "c");
        assertEquals(3, rm.asMapOfRanges().size());
        assertTrue(rs.asRanges().contains(Range.closed(1, 4)));
        assertEquals("c", rm.get(3));
    }
}