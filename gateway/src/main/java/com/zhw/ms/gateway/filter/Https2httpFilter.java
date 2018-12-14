package com.zhw.ms.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;


/**
 * @author innerpeacez
 * @since 2018/12/14
 * <p>
 * 将https请求转换成http请求，外部访问gateway使用https,经过gateway访问内部服务转换成http请求
 */
@Component
public class Https2httpFilter implements GlobalFilter, Ordered {

    // LoadBalancerClientFilter的Order为10100，需要在其执行之前修改https请求为http请求
    private static final int HTTPS_2_HTTP_FILTER_ORDER = 10099;

    private static final String http = "http";

    private static final String https = "https";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI originalUri = exchange.getRequest().getURI();
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        String forwardUri = request.getURI().toString();
        if (!StringUtils.isEmpty(forwardUri) || forwardUri.startsWith(this.https)) {
            try {
                URI mutatedUri = new URI(this.http,
                        originalUri.getUserInfo(),
                        originalUri.getHost(),
                        originalUri.getPort(),
                        originalUri.getPath(),
                        originalUri.getQuery(),
                        originalUri.getFragment());
                mutate.uri(mutatedUri);
            } catch (URISyntaxException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }
        ServerHttpRequest build = mutate.build();

        return chain.filter(exchange.mutate().request(build).build());
    }


    @Override
    public int getOrder() {
        return HTTPS_2_HTTP_FILTER_ORDER;
    }
}
