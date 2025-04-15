package com.sandeep.api_gateway.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("post-service", r -> r.path("/PostAPI/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://post-service")) // Use "lb://" for service discovery
                .route("post-service", r -> r.path("/publicPost/**")
                        .uri("lb://post-service"))
                .route("profile-service", r -> r.path("/ProfileAPI/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://profile-service"))
                .route("profile-service", r -> r.path("/publicProfileAPI/**")
                        .uri("lb://profile-service"))
                .build();
    }
}