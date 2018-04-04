package com.asiainfo.guava.collectionsutility;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年3月29日  下午4:09:04
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class CollectionUtilityExample {

    /** 
     * TODO
     * 
     * @param args
     */
    public static void main(String[] args) {
        
        // Iterables
        List<String> list = Lists.newArrayList("a", "b", "c");
        assertEquals("a", Iterables.get(list, 0));
        assertEquals("defalut", Iterables.get(list, 3, "defalut"));
        assertEquals("a", Iterables.getFirst(list, "defalut"));
        assertEquals("c", Iterables.getLast(list));
        // concat 返回包含两个List的Iterable对象，不会生成新的List
        Iterable<String> iterable =  Iterables.concat(list, Lists.newArrayList("c", "d"));
        list.remove(0);
        assertArrayEquals(new String[] {"b", "c", "c", "d"}, Iterables.toArray(iterable, String.class));
        assertEquals(2, Iterables.frequency(iterable, "c"));
        // 返回指定个数的视图
        assertArrayEquals(new String[] {"b", "c"}, Iterables.toArray(Iterables.limit(iterable, 2), String.class));
        // 返回不可变视图
        iterable = Iterables.unmodifiableIterable(iterable);
        Iterator<String> it = iterable.iterator();
        try {
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
        } catch (Exception ex) {
            System.out.println(ex.getClass());
        }
        
        // Lists
        // 初始化容量
        Lists.newArrayListWithCapacity(10);
        Lists.newArrayListWithExpectedSize(10);
        Lists.newLinkedList();
        // reverse 返回新的列表
        assertEquals(Lists.newArrayList("c", "b", "a"), Lists.reverse(Lists.newArrayList("a", "b", "c")));
        // partition返回指定大小子列表视图，任何对列表、子列表的修改都会引发视图变化
        list = Lists.newArrayList("a", "b", "c", "d", "e");
        List<List<String>> lists = Lists.partition(list, 2);
        assertEquals(3, lists.size());
        assertArrayEquals(new String[] {"a", "b"}, Iterables.toArray(lists.get(0), String.class));
        list.remove(0);
        assertEquals(2, lists.size());
        assertArrayEquals(new String[] {"b", "c"}, Iterables.toArray(lists.get(0), String.class));
        
        // Sets
        Sets.newHashSet();
        Sets.newLinkedHashSet();
        Sets.newTreeSet();
        Sets.newTreeSet(Ordering.natural());
        // 集合运算(返回只读的SetView)
        // 并集
        SetView<Integer> view = Sets.union(Sets.newHashSet(1, 2, 3), Sets.newHashSet(3, 4, 5));
        assertEquals(5, view.size());
        // 差集
        view = Sets.intersection(Sets.newHashSet(1, 2, 3), Sets.newHashSet(3, 4, 5));
        assertEquals(1, view.size());
        // 取集合1 不在集合2 中的元素视图
        view = Sets.difference(Sets.newHashSet(1, 2, 3), Sets.newHashSet(3, 4, 5));
        assertEquals("[1, 2]", view.toString());
        // 交差取反
        view = Sets.symmetricDifference(Sets.newHashSet(1, 2, 3), Sets.newHashSet(3, 4, 5));
        assertEquals("[1, 2, 4, 5]", view.toString());
        // 笛卡尔
        Set<List<Integer>> cart = Sets.cartesianProduct(Lists.newArrayList(Sets.newHashSet(1, 2), Sets.newHashSet(3, 4, 5)));
        assertEquals(6, cart.size());
        // 返回所有子集(包含空集合)
        Set<Set<Integer>> power = Sets.powerSet(Sets.newHashSet(1, 2, 3));
        assertEquals(8, power.size());

        // Maps
        Maps.newHashMap();
        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("b", 2, "c", 4, "d", 5);
        MapDifference<String, Integer> diff = Maps.difference(left, right);
        assertFalse(diff.areEqual());
        assertEquals("{b=2}", diff.entriesInCommon().toString());
        assertEquals("{c=(3, 4)}", diff.entriesDiffering().toString());
    }
}
