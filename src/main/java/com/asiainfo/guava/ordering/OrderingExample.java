package com.asiainfo.guava.ordering;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年3月28日  下午5:23:42
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class OrderingExample {

    /** 
     * TODO
     * 
     * @param args
     */
    public static void main(String[] args) {
        
        // 常用静态方法
        // Ordering.natural();                  // 使用Comparable类型的自然顺序， 例如：整数从小到大，字符串是按字典顺序
        // Ordering.usingToString();            // 使用toString()返回的字符串按字典顺序进行排序
        // Ordering.from(Comparator);           // 将Comparator转换为Ordering

        // 实例方法(支持链式)
        // reverse();                            //返回与当前Ordering相反的排序
        // nullsFirst();                         //返回一个将null放在non-null元素之前的Ordering，其他的和原始的Ordering一样
        // nullsLast();                          //返回一个将null放在non-null元素之后的Ordering，其他的和原始的Ordering一样
        // compound(Comparator);                 //返回一个使用Comparator的Ordering，Comparator作为第二排序元素
        // lexicographical();                    //返回一个按照字典元素迭代的Ordering
        // onResultOf(Function);                 //将function应用在各个元素上之后, 在使用原始ordering进行排序
        // greatestOf(Iterable iterable, int k); //返回指定的前k个可迭代的最大的元素，按照当前Ordering从最大到最小的顺序
        // leastOf(Iterable iterable, int k);    //返回指定的前k个可迭代的最小的元素，按照当前Ordering从最小到最大的顺序
        // isOrdered(Iterable);                  //是否有序(前面的元素可以大于或等于后面的元素)，Iterable不能少于2个元素
        // isStrictlyOrdered(Iterable);          //是否严格有序(前面的元素必须大于后面的元素)，Iterable不能少于两个元素
        // sortedCopy(Iterable);                 //返回指定的元素作为一个列表的排序副本
        
        natural();
        from();
        reverse();
        nullFirst();
        compound();
        onResultOf();
        greatestOf();
        leastOf();
        isOrdered();
        isStrictlyOrdered();
        sortedCopy();
        chain();
    }

    // 使用Comparable类型的自然顺序， 例如：整数从小到大，字符串是按字典顺序
    public static void natural() {
        
        // test int order
        List<Integer> unorderedIntList = Lists.newArrayList(5, 3, 2, 4, 1);
        List<Integer> orderedIntList = Lists.newArrayList(1, 2, 3, 4, 5);
        Collections.sort(unorderedIntList, Ordering.natural());
        assertTrue(orderedIntList.equals(unorderedIntList));

        // test string order
        List<String> unorderedStringList = Lists.newArrayList("Test", "Jerry", "Rock", "Ohaha", "Yeah");
        List<String> orderedStringList = Lists.newArrayList("Jerry", "Ohaha", "Rock", "Test", "Yeah");
        Collections.sort(unorderedStringList, Ordering.natural());
        assertTrue(orderedStringList.equals(unorderedStringList));
    }

    // 自定义Comparator
    public static void from() {
        
        List<Integer> unorderedIntList = Lists.newArrayList(5, 3, 2, 4, 1);
        List<Integer> orderedIntList = Lists.newArrayList(1, 2, 3, 4, 5);
        Collections.sort(unorderedIntList, Ordering.from(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i1.compareTo(i2);
            }
        }));
        assertTrue(orderedIntList.equals(unorderedIntList));
    }

    // 先进行natural排序再reverse
    public static void reverse() {
        
        List<Integer> unorderedIntList = Lists.newArrayList(3, 4, 2, 1, 5);
        List<Integer> orderedIntList = Lists.newArrayList(5, 4, 3, 2, 1);
        Collections.sort(unorderedIntList, Ordering.natural().reverse());
        assertTrue(orderedIntList.equals(unorderedIntList));
    }

    // 返回一个将null放在non-null元素之前的Ordering，其他的和原始的Ordering一样
    public static void nullFirst() {
        
        List<Integer> unorderedIntList = Lists.newArrayList(5, 3, null, 4, 1);
        List<Integer> orderedIntList = Lists.newArrayList(null, 1, 3, 4, 5);
        Collections.sort(unorderedIntList, Ordering.natural().nullsFirst());
        assertTrue(orderedIntList.equals(unorderedIntList));
    }

    // 多重排序排序
    public static void compound() {
        
        List<String> unorderedStringList = Lists.newArrayList("Oest", "Jerry", "Jock", "Ohaha", "Yeah");
        List<String> orderedStringList = Lists.newArrayList("Jock", "Jerry", "Ohaha", "Oest", "Yeah");
        Ordering<String> firstLetterOrdering = Ordering.from(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.substring(0, 1).compareTo(s2.substring(0, 1));
            }
        });
        Collections.sort(unorderedStringList, firstLetterOrdering.compound(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.substring(1, s2.length()).compareTo(s1.substring(1, s1.length()));
            }
        }));
        assertTrue(orderedStringList.equals(unorderedStringList));
    }
    
    // 对排序元素进行转换
    public static void onResultOf() {
        
        List<String> unorderedStringList = Lists.newArrayList("Oest", "Jarry", "Jock", "Ohaha", "Ybah");
        List<String> orderedStringList = Lists.newArrayList("Jarry", "Ybah", "Oest", "Ohaha", "Jock");
        Ordering<String> secondLetterOrdering = Ordering.natural().onResultOf(new Function<String, String>() {
            @Override
            public String apply(String input) {
                // 去除首字母
                return input.substring(1, input.length());
            }
        });
        Collections.sort(unorderedStringList, secondLetterOrdering);
        assertTrue(orderedStringList.equals(unorderedStringList));
    }

    // 返回指定的前k个可迭代的最大的元素，按照当前Ordering从最大到最小的顺序
    public static void greatestOf() {
        
        List<Integer> unorderList = Lists.newArrayList(5, 3, 2, 4, 1);
        List<Integer> orderList = Lists.newArrayList(5, 4);
        assertTrue(orderList.equals(Ordering.natural().greatestOf(unorderList, 2)));
        orderList = Lists.newArrayList(5, 4, 3, 2, 1);
        assertTrue(orderList.equals(Ordering.natural().greatestOf(unorderList, 8)));
    }

    // 返回指定的前k个可迭代的最小的元素，按照当前Ordering从最小到最大的顺序
    public static void leastOf() {
        
        List<Integer> unorderList = Lists.newArrayList(5, 3, 2, 4, 1);
        List<Integer> orderList = Lists.newArrayList(1, 2);
        assertTrue(orderList.equals(Ordering.natural().leastOf(unorderList, 2)));
        orderList = Lists.newArrayList(1, 2, 3, 4, 5);
        assertTrue(orderList.equals(Ordering.natural().leastOf(unorderList, 8)));
    }

    // 是否有序(前面的元素可以大于或等于后面的元素)，Iterable不能少于2个元素
    public static void isOrdered() {
        
        // 大于可通过
        List<Integer> orderList = Lists.newArrayList(1, 2, 3, 4, 5);
        assertTrue(Ordering.natural().isOrdered(orderList));

        // 大于或等于也可通过
        orderList = Lists.newArrayList(1, 2, 2, 4, 5);
        assertTrue(Ordering.natural().isOrdered(orderList));
    }

    // 是否严格有序(前面的元素必须大于后面的元素)，Iterable不能少于两个元素
    public static void isStrictlyOrdered() {
        
        // 大于可通过
        List<Integer> orderList = Lists.newArrayList(1, 2, 3, 4, 5);
        assertTrue(Ordering.natural().isStrictlyOrdered(orderList));

        // 大于或等于不可通过
        orderList = Lists.newArrayList(1, 2, 2, 4, 5);
        assertFalse(Ordering.natural().isStrictlyOrdered(orderList));
    }

    // 返回指定的元素作为一个列表的排序副本
    public static void sortedCopy() {
        
        List<Integer> unorderList = Lists.newArrayList(5, 3, 2, 4, 1);
        List<Integer> orderList = Lists.newArrayList(1, 2, 3, 4, 5);
        assertTrue(orderList.equals(Ordering.natural().sortedCopy(unorderList)));
    }
    
    // chain
    public static void chain() {
        
        List<String> list = Lists.newArrayList("10", "12", "9", null, "1", "2");
        Ordering<String> chain = Ordering.natural().reverse().nullsFirst()
                .onResultOf(new Function<String, String>() {
                    @Override
                    public String apply(String input) {
                        return null == input ? null : input.substring(input.length() - 1, input.length());
                    }
        });
        Collections.sort(list, chain);
        System.out.println(list);
    }
}
