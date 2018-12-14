package com.zhw.ms.serviceconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author innerpeacez
 * @since 2018/12/13
 */
@FeignClient(name = "provider",url = "http://localhost:8000/")
public interface HelloFeignService {

    @GetMapping("/hello")
    String hello(@RequestParam String name);
}
