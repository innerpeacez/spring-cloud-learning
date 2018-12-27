package com.zhw.ms.zuulserver.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author innerpeacez
 * @since 2018/12/27
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Autowired
    private ZuulProperties zuulProperties;

    @Bean
    @Primary
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        return () -> {
            List<SwaggerResource> resources = new ArrayList<>();
            zuulProperties.getRoutes()
                    .values()
                    .stream()
                    .forEach(route -> resources.add(createSwaggerResource(route.getServiceId(),route.getServiceId(),"2.0")));
            return resources;
        };
    }

    private SwaggerResource createSwaggerResource(String name ,String location,String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation("/" + location + "/v2/api-docs");
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
