package com.zhw.ms.configclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 类描述: 实现自动刷新客户端配置
 *
 * @author innerpeacez
 * @since 2018/12/13 9:04
 */
@Configuration
@Slf4j
public class AutoRefreshConfiguration {

    @Autowired
    private ContextRefresher contextRefresher;

    /**
     * 定时去执行某个方法，refresh方法可以让客户端去拉取配置文件
     * fixedRate 一分钟刷新一次配置
     * initialDelay 启动一分钟之后开始执行
     */
    @Scheduled(fixedRate = 60000L, initialDelay = 60000L)
    public void autoRefreshConfig() {
        log.info("刷新配置！");
        contextRefresher.refresh();
    }

}