package com.zhw.ms.zuulserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
//@EnableOAuth2Sso
public class ZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/login","/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .csrf()
//                .disable();
//    }
}

