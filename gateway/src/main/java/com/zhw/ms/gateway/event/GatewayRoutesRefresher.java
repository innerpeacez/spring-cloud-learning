package com.zhw.ms.gateway.event;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author innerpeacez
 * @since 2018/12/14
 * <p>
 * gateway动态路由事件
 */
@Component
public class GatewayRoutesRefresher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void refreshRoutes() {
        publisher.publishEvent(new RefreshRoutesEvent(this));
    }
}