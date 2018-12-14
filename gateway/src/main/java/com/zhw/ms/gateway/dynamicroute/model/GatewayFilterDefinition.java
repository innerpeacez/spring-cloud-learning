package com.zhw.ms.gateway.dynamicroute.model;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author innerpeacez
 * @since 2018/12/14
 * 路由过滤器模型
 */
@Data
public class GatewayFilterDefinition {

    /**
     * 过滤器名
     */
    private String name;

    /**
     * 对应路由规则
     */
    private Map<String, String> routeRules = new LinkedHashMap<>();
}
