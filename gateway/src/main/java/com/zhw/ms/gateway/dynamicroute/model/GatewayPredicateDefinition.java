package com.zhw.ms.gateway.dynamicroute.model;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author innerpeacez
 * @since 2018/12/14
 * 路由断言模型
 */
@Data
public class GatewayPredicateDefinition {

    /**
     * 路由断言名称
     */
    private String name;

    /**
     * 路由断言规则
     */
    private Map<String, String> predicateRoles = new LinkedHashMap<>();
}
