package com.zhw.ms.gateway.dynamicroute.controller;

import com.zhw.ms.gateway.dynamicroute.model.GatewayPredicateDefinition;
import com.zhw.ms.gateway.dynamicroute.model.GatewayRouteDefinition;
import com.zhw.ms.gateway.dynamicroute.service.DynamicRouteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author innerpeacez
 * @since 2018/12/14
 */
@RestController
@RequestMapping("/route")
public class DynamicRouteController {

    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;

    /**
     * 增加路由
     *
     * @param gatewayRouteDefinition
     * @return
     */
    @PostMapping
    public String addRoute(@RequestBody GatewayRouteDefinition gatewayRouteDefinition) {
        try {
            RouteDefinition definition = assembleRouteDefinition(gatewayRouteDefinition);
            return this.dynamicRouteService.addRoute(definition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 删除路由
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> deleteRoute(@PathVariable String id) {
        return this.dynamicRouteService.deleteRoute(id);
    }

    /**
     * 更新路由
     *
     * @param gatewayRouteDefinition
     * @return
     */
    @PutMapping
    public String updateRoute(@RequestBody GatewayRouteDefinition gatewayRouteDefinition) {
        RouteDefinition definition = assembleRouteDefinition(gatewayRouteDefinition);
        return this.dynamicRouteService.updateRoute(definition);
    }

    private RouteDefinition assembleRouteDefinition(GatewayRouteDefinition gatewayRoutedefinition) {
        RouteDefinition definition = new RouteDefinition();
        List<PredicateDefinition> predicateDefinitions = new ArrayList<>();
        definition.setId(gatewayRoutedefinition.getId());
        List<GatewayPredicateDefinition> gatewayPredicateDefinitionList = gatewayRoutedefinition.getPredicates();
        for (GatewayPredicateDefinition gatewayPredicateDefinition : gatewayPredicateDefinitionList) {
            PredicateDefinition predicate = new PredicateDefinition();
            predicate.setArgs(gatewayPredicateDefinition.getPredicateRoles());
            predicate.setName(gatewayPredicateDefinition.getName());
            predicateDefinitions.add(predicate);
        }
        definition.setPredicates(predicateDefinitions);
        URI uri = UriComponentsBuilder.fromHttpUrl(gatewayRoutedefinition.getUri()).build().toUri();
        definition.setUri(uri);
        return definition;
    }

}
