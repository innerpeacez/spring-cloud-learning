package com.zhw.ms.serviceconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author innerpeacez
 * @since 2018/12/14
 */
@RestController
public class HelloFeignController {

    @Autowired
    private HelloFeignService feignService;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return feignService.hello(name) + "\n" + new Date().toString();
    }
}
