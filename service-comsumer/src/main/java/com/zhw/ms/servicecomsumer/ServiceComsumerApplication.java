package com.zhw.ms.servicecomsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
@RestController
@EnableFeignClients
@RequestMapping("/hello")
public class ServiceComsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceComsumerApplication.class, args);
    }

    @Autowired
    HelloFeignService helloFeignService;

    @GetMapping("/{name}")
    public String index(@PathVariable("name") String name) {
        return helloFeignService.hello(name) + "\n" + new Date().toString();
    }
}

