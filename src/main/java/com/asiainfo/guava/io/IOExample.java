package com.asiainfo.guava.io;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.io.Resources;

/**
 * 接受流为参数的Guava方法不会关闭这个流：关闭流的职责通常属于打开流的代码块。
 * 
 * @author       zq
 * @date         2018年4月1日  下午7:28:55
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class IOExample {

    /** 
     * TODO
     * 
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        
        String filePath = IOExample.class.getClassLoader().getResource("com/asiainfo/guava/io/test.txt").getPath();
        File file = new File(filePath);
        
        // asCharSource，read完成后会自动关闭源
        // list
        ImmutableList<String> lines = Files.asCharSource(file, Charsets.UTF_8).readLines();
        for (String line : lines) {
            System.out.println(line);
        }
        // string
        System.out.println(Files.asCharSource(file, Charsets.UTF_8).read());
        // asByteSource
        byte[] bt = Files.asByteSource(file).read();
        System.out.println(new String(bt, Charsets.UTF_8));
        System.out.println(Splitter.on("\r\n").trimResults().omitEmptyStrings().split(Files.asCharSource(file, Charsets.UTF_8).read()));

        // asCharSink
        Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND).write("\r\nabc");
        // asByteSink
        Files.asByteSink(file, FileWriteMode.APPEND).write("\r\n123".getBytes());
        
        // url
        System.out.println(new String(Resources.asByteSource(new URL("http://10.1.235.48:18084/")).read()));
    }
}
