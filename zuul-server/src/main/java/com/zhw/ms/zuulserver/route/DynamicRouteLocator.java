package com.zhw.ms.zuulserver.route;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author innerpeacez
 * @since 2018/12/27
 */
@RefreshScope
public class DynamicRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    private ZuulProperties properties;

    public DynamicRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
    }

    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {

        LinkedHashMap<String, ZuulRoute> routesMap = new LinkedHashMap<>();
        //从配置文件中加载路由信息
        routesMap.putAll(super.locateRoutes());
        //优化一下配置
        LinkedHashMap<String, ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }
        return values;
    }
}
