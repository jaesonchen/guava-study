package com.asiainfo.guava.stringutil;

import java.util.Arrays;
import java.util.Map;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * google 字符串处理
 * 
 * @author       zq
 * @date         2018年4月1日  下午7:25:41
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class StringUtilExample {

    /** 
     * TODO
     * 
     * @param args
     */
    public static void main(String[] args) {
        
        // Joiner 不设置null处理方式时会抛出NullPointerException
        // 忽略null
        System.out.println(Joiner.on("; ").skipNulls().join("Harry", null, "Ron", "Hermione"));
        // 替换null
        System.out.println(Joiner.on(", ").useForNull(" ").join("Harry", null, "Ron", "Hermione"));
        // appendTo
        System.out.println(Joiner.on(", ").appendTo(new StringBuilder(), 
                Lists.newArrayList("Harry", "Ron", "Hermione")).toString());
        // Map
        Map<String, Object> map = Maps.newHashMap();
        map.put("k1", "v1");
        map.put("k2", "v2");
        System.out.println(Joiner.on("; ").withKeyValueSeparator("-").join(map));

        // Splitter
        // java自带的split处理空串结果 "", "a", "", "b"
        System.out.println(Arrays.asList(",a,,b,".split(",")));
        // a, b
        System.out.println(
                Splitter.on(',')
                    .trimResults()  // 去掉空格
                    .omitEmptyStrings() //去掉空串
                    .split(",a,, , b,"));
        
        // CharMatcher
        // is
        System.out.println(Splitter.on(',')
                    .trimResults(CharMatcher.is('a'))
                    .split(",a, , b,"));
        // anyOf
        System.out.println(Splitter.on(',')
                .trimResults(CharMatcher.anyOf("cd"))
                .split(",a,c, b,"));
        // whitespace
        System.out.println(Splitter.on(',')
                .trimResults(CharMatcher.whitespace())
                .split(",a, , b,"));
        // inRange
        System.out.println(Splitter.on(',')
                .trimResults(CharMatcher.inRange('0', '9'))
                .split(",a, 8, b,"));
        
        
        // CaseFormat
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CHANNEL_ID"));
    }
}
