package com.asiainfo.guava.cache;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.util.concurrent.UncheckedExecutionException;

/**
 * TODO
 * 
 * @author       zq
 * @date         2018年3月27日  下午3:23:33
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class CacheExample {

    /** 
     * TODO
     * 
     * @param args
     * @throws ExecutionException 
     */
    public static void main(String[] args) throws ExecutionException {

        cacheLoader();
        System.out.println("===============================");
        callable();
        System.out.println("===============================");
        eviction();
        System.out.println("===============================");
        stats();
    }

    // LoadingCache.get(K)方法可以获取缓存中对应的值，如果没有缓存，则会使用CacheLoader原子地加载新值.
    public static void cacheLoader() {
        
        LoadingCache<String, Object> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(5, TimeUnit.SECONDS)
                .build(
                    new CacheLoader<String, Object>() {
                        public Object load(String key) throws Exception {
                            if (StringUtils.isEmpty(key)) {
                                throw new IllegalArgumentException("key is empty!");
                            }
                            System.out.println("load from db!");
                            return key + "_" + UUID.randomUUID().toString().replace("-", "");
                        }
                    });
        try {
            System.out.println("first access:" + cache.get("k1"));
            cache.put("k1", "v2");
            System.out.println("second access:" + cache.get("k1"));
            // 不会引起加载
            System.out.println("getIfPresent:" + cache.getIfPresent("k2"));
            TimeUnit.SECONDS.sleep(5);
            System.out.println("after 5 second access:" + cache.get("k1"));
            
            System.out.println("load exception:" + cache.get(""));
        } catch (ExecutionException | InterruptedException | UncheckedExecutionException e) {
            e.printStackTrace();
        }
        
        // key为null时会抛出空指针异常
        try {
            System.out.println("null access:" + cache.get(null));
        } catch (ExecutionException | NullPointerException e) {
            e.printStackTrace();
        }
    }
    
    // get(K, Callable)方法尝试返回缓存中对应的值; 如果值不存在，则使用Callable运算，并把结果加入缓存中。
    public static void callable() {
        
        Cache<String, Object> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build();
        try {
            String key = "k1";
            cache.get(key, new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    // 缓存不存在，就会调用call()方法计算, 并把结果加入缓存
                    System.out.println(key + " load!");
                    return key + "_" + UUID.randomUUID().toString().replace("-", "");
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    
    // guava提供了三种基本的缓存回收方式: 基于容量回收、定时回收和基于引用回收。
    public static void eviction() {
        
        LoadingCache<String, Object> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)  //容量回收，LRU
                .expireAfterAccess(5, TimeUnit.SECONDS) //定时回收
                .weakValues()   //引用回收
                .removalListener(new RemovalListener<String, Object>() {
                    public void onRemoval(RemovalNotification<String, Object> removal) {
                        // Object obj = removal.getValue();
                        // tear down properly
                    }})
                .build(
                    new CacheLoader<String, Object>() {
                        public Object load(String key) throws Exception {
                            if (StringUtils.isEmpty(key)) {
                                throw new IllegalArgumentException("key is empty!");
                            }
                            System.out.println("load from db!");
                            return key + "_" + UUID.randomUUID().toString().replace("-", "");
                        }
                    });
        
        try {
            cache.get("k1");
            // 显式移除
            cache.invalidate("k1");
            cache.get("k1");
            cache.invalidateAll();
            
            // 刷新和回收不一样，刷新时，缓存仍然可以向其他线程返回旧值，而回收时，读取线程必须等待新值加载完成
            cache.get("k2");
            cache.refresh("k2");
            cache.cleanUp();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    
    // CacheBuilder.recordStats() 开启Guava Cache的统计功能。
    // Cache.stats() 返回CacheStats对象
    public static void stats() throws ExecutionException {
        
        LoadingCache<String, Object> cache = CacheBuilder.newBuilder()
                .recordStats()
                .expireAfterAccess(5, TimeUnit.SECONDS)
                .build(
                    new CacheLoader<String, Object>() {
                        public Object load(String key) throws Exception {
                            if (StringUtils.isEmpty(key)) {
                                throw new IllegalArgumentException("key is empty!");
                            }
                            System.out.println("load from db!");
                            return key + "_" + UUID.randomUUID().toString().replace("-", "");
                        }
                    });
        
        cache.put("k1", "v1");
        cache.get("k1");
        cache.get("k2");
        cache.get("k3");
        
        CacheStats stats = cache.stats();
        // 缓存命中率
        System.out.println(stats.hitRate());
        // 缓存命中数量
        System.out.println(stats.hitCount());
        // 加载新值的平均时间，单位为纳秒
        System.out.println(stats.averageLoadPenalty());
        // 缓存项被回收的总数，不包括显式清除
        System.out.println(stats.evictionCount());
    }
    
    // Map视图
    // cache.asMap() 提供了缓存的ConcurrentMap形式
    public static void mapView() {
        //asMap()包含当前所有加载到缓存的项
        //asMap().get(key)实质上等同于cache.getIfPresent(key)，而且不会引起缓存项的加载
        //Cache.asMap().get(Object)方法和Cache.asMap().put(K, V)方法会重置相关缓存项的访问时间
    }
}
