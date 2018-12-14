package com.zhw.ms.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;


/**
 * @author innerpeacez
 * @since 2018/12/14
 */
@Component
public class RewritePathFilter implements GlobalFilter, Ordered {

    private static String regexp = "/(?<segment>.*)";

    private static String replacement = "/$\\{segment}";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String rawPath1 = exchange.getRequest().getURI().getRawPath();
        String substring = rawPath1.substring(1);
        int i = substring.indexOf("/");
        String substring1 = "/" + substring.substring(0, i) + regexp;
        String regexpPath = exchange.getRequest().getURI().getRawPath() + regexp;
        String rawPath = exchange.getRequest().getURI().getRawPath().replaceAll(substring1, replacement);
        ServerHttpRequest req = exchange.getRequest();
        ServerHttpRequest request = req.mutate()
                .path(rawPath)
                .build();
        exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, request.getURI());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
