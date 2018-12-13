package com.zhw.ms.configclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;

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
    private ConfigServicePropertySourceLocator locator;

    @Autowired
    private AbstractEnvironment environment;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ConfigurableEnvironment configurableEnvironment;

    @Autowired
    private ContextRefresher contextRefresher;

    /**
     * 定时去执行某个方法，refresh方法可以让客户端去拉取配置文件
     * fixedRate 一分钟刷新一次配置
     * initialDelay 启动一分钟之后开始执行
     */
    @Scheduled(fixedRate = 2000L, initialDelay = 2000L)
    public void autoRefreshConfig() {
        log.info("刷新配置！");
        contextRefresher.refresh();
//        PropertySource<?> locate = locator.locate(this.environment);


//        MutablePropertySources propertySources = environment.getPropertySources();
//        propertySources.addLast(locate);
//        PropertySource<?> propertySource = propertySources.get("bootstrapProperties");
//        if (!StringUtils.hasText((CharSequence) propertySources.get(locate.getName()))) {
//            propertySources.addAfter(locate.getName(),locate);
//        } else {
//            propertySources.replace(locate.getName(),locate);
//        }
    }

}