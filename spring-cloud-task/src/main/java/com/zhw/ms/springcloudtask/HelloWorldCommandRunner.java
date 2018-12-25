package com.zhw.ms.springcloudtask;

import org.springframework.boot.CommandLineRunner;

/**
 * @author innerpeacez
 * @since 2018/12/25
 */
public class HelloWorldCommandRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello World");
    }
}
