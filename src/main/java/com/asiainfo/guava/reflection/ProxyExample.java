package com.asiainfo.guava.reflection;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年4月1日  下午7:43:25
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class ProxyExample {

    /** 
     * TODO
     * 
     * @param args
     */
    public static void main(String[] args) {

        TransactionProxy tp = new TransactionProxy();
        IService service = tp.bind(new ServiceImpl());
        service.service();
    }
}
