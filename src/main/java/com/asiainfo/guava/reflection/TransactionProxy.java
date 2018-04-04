package com.asiainfo.guava.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.google.common.reflect.Reflection;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年4月1日  下午7:40:48
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class TransactionProxy implements InvocationHandler {

    private IService target;
    
    public IService bind(IService target) {
        this.target = target;
        return Reflection.newProxy(IService.class, this);
    }
    
    /* 
     * TODO
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("Start transaction!");
        Object o = method.invoke(target, args);
        System.out.println("Submit transaction!");
        return o;
    }
}
