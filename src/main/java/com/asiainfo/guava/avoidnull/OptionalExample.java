package com.asiainfo.guava.avoidnull;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.base.Optional;

/**
 * null的处理，特别在缓存时，null容易导致缓存击穿。
 * 
 * @author       zq
 * @date         2018年3月29日  下午4:11:08
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class OptionalExample {

    /** 
     * TODO
     * 
     * @param args
     */
    public static void main(String[] args) {

        // Optional.of
        Optional<Integer> optional = Optional.of(5);
        // contains a (non-null) instance
        assertTrue(optional.isPresent());
        assertEquals(Integer.valueOf(5), optional.get());
        // Optional.of不能用于null
        try {
            Optional.of(null);
        } catch(NullPointerException e) {
            System.out.println("Optional.of(null) throws NullPointerException.");
        }
        
        // Optional.absent == Optional.fromNullable(null)
        Optional<Integer> nullOptional = Optional.absent();
        assertFalse(nullOptional.isPresent());
        // Optional.fromNullable
        nullOptional = Optional.fromNullable(null);
        assertFalse(nullOptional.isPresent());
        
        // avoiding return null
        assertEquals(Integer.valueOf(0), Optional.fromNullable(null).or(0));
        assertEquals(null, Optional.fromNullable(null).orNull());
    }
}
