package com.zhw.ms.servicecomsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author innerpeacez
 * @since 2018/12/13
 */
@FeignClient
public interface HelloFeignService {

    @GetMapping("/hello")
    String hello(String name);
}
