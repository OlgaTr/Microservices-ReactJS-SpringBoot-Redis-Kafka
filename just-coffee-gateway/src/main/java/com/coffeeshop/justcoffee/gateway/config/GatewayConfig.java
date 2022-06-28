package com.coffeeshop.justcoffee.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gateway(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(routeSpec -> routeSpec.path("/coffee", "/coffee/customCoffee/**", "/toppings")
                        .uri("http://localhost:8080"))
                .route(routeSpec -> routeSpec.path("/customCoffees", "/orders")
                        .uri("http://localhost:8081"))
                .route(routeSpec -> routeSpec.path("/users", "/users/signIn")
                        .uri("http://localhost:8082"))
//                .route(routeSpec -> routeSpec.path("/orders")
//                        .filters(gatewayFilterSpec -> gatewayFilterSpec.setPath("/users/authenticate"))
//                        .uri("http://localhost:8082"))
                .build();
    }
}
