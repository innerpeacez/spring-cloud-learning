package com.zhw.ms.zuulserver.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author innerpeacez
 * @since 2018/12/27
 */
@Configuration
public class DynamicZuulConfig {

    @Autowired
    ZuulProperties zuulProperties;

    @Autowired
    protected ServerProperties server;

    @Bean
    public DynamicRouteLocator routeLocator() {
        DynamicRouteLocator routeLocator = new DynamicRouteLocator(this.server.getServlet().getServletPrefix(),
                this.zuulProperties);
        return routeLocator;
    }
}
