package com.asiainfo.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年4月1日  下午7:32:59
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class EventBusExample {
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        //publisher
        EventBus eb = new EventBus("multi");
        eb.register(new MyListener());
        eb.post(new Integer(100));
        eb.post(new Integer(200));
        eb.post(new Long(300));
        eb.post(new Long(400));
    }
    

}

class MyListener {
    
    @Subscribe
    public void listenInteger(Integer i) {
        System.out.println("Integer: " + i);
    }
    
    @Subscribe
    public void listenLong(Long l) {
        System.out.println("Long: " + l);
    }
}
