package com.zhw.ms.gateway.dynamicroute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author innerpeacez
 * @since 2018/12/14
 * 动态路由实现类
 */
@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    /**
     * 增加路由
     *
     * @param routeDefinition
     * @return
     */
    public String addRoute(RouteDefinition routeDefinition) {
        this.routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return "SUCCESS";
    }

    /**
     * 删除路由
     *
     * @param id
     * @return
     */
    public Mono<ResponseEntity<Object>> deleteRoute(String id) {
        return this.routeDefinitionWriter.delete(Mono.just(id)).then(Mono.defer(() -> Mono.just(ResponseEntity.ok().build())))
                .onErrorResume(t -> t instanceof NotFoundException, t -> Mono.just(ResponseEntity.notFound().build()));
    }

    /**
     * 更新路由
     *
     * @param routeDefinition
     * @return
     */
    public String updateRoute(RouteDefinition routeDefinition) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(routeDefinition.getId()));
        } catch (Exception e) {
            return "update fail,not find route routeId: " + routeDefinition.getId();
        }
        try {
            this.routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return "SUCCESS";
        } catch (Exception e) {
            return "update route fail";
        }
    }

}
