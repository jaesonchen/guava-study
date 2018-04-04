package com.asiainfo.guava.throwables;

//import com.google.common.base.Throwables;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年3月29日  下午4:19:29
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class ThrowablesExample {

    /** 
     * TODO
     * 
     * @param args
     */
    public static void main(String[] args) {

        // 抛出指定类型的异常
        // <X extends Throwable> void throwIfInstanceOf(Throwable throwable, Class<X> declaredType) throws X
        // 抛出RuntimeException、Error非受检异常
        // void throwIfUnchecked(Throwable throwable)
        // 抛出指定类型异常和非受检异常
        // <X extends Throwable> void propagateIfPossible(@NullableDecl Throwable throwable, Class<X> declaredType) throws X
        // 返回异常触发点
        // Throwable getRootCause(Throwable throwable)
        // 返回string的stacktrace
        // String getStackTraceAsString(Throwable throwable)
    }
}
