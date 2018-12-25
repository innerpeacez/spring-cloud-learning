package com.zhw.ms.springcloudtask;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author innerpeacez
 * @since 2018/12/25
 */
@Component
public class SampleTask {

    @Bean
    public ApplicationRunner applicationRunner2() {
        return (xxx) -> System.out.println("Hello World2");
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return (xxx) -> System.out.println("Hello World");
    }

}
