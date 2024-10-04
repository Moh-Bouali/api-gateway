package com.individual_s7.api_gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Routes {

    @Bean
    public RouteLocator userServiceRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Route for user registration that bypasses authentication
                .route("user-register", r -> r
                        .path("/api/user/register")
                        .uri("http://localhost:8080")
                )
                // Route for authenticated user service calls
                .route("user-service", r -> r
                        .path("/api/user/**")  // Match user service paths
                        // We don't remove the Authorization header
                        .uri("http://localhost:8080")  // Forward to the User Service URI
                )
                .route("friendship-service", r -> r
                        .path("/api/friendship/**")  // Match user service paths
                        // We don't remove the Authorization header
                        .uri("http://localhost:8085")  // Forward to the User Service URI
                )
                .build();
    }
}

