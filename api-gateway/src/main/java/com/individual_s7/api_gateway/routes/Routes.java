package com.individual_s7.api_gateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.socket.server.RequestUpgradeStrategy;
import org.springframework.web.reactive.socket.server.upgrade.ReactorNettyRequestUpgradeStrategy;

@Configuration
public class Routes {
    @Value("${user.service.url}")
    private String userServiceUrl;

    @Value("${friendship.service.url}")
    private String friendshipServiceUrl;

    @Value("${post.service.url}")
    private String postServiceUrl;

    @Value("${notification.service.url}")
    private String notificationServiceUrl;

    @Bean
    public RouteLocator userServiceRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Route for user registration that bypasses authentication
                .route("user-register", r -> r
                        .path("/api/user/register")
                        .uri(userServiceUrl)
                )
                // Route for authenticated user service calls
                .route("user-service", r -> r
                        .path("/api/user/**")  // Match user service paths
                        .uri(userServiceUrl)  // Forward to the User Service URI
                )
                .route("friendship-service", r -> r
                        .path("/api/friendship/**")  // Match friendship service paths
                        .uri(friendshipServiceUrl)  // Forward to the Friendship Service URI
                )
                .route("post-service", r -> r
                        .path("/api/post/**")  // Match post service paths
                        .uri(postServiceUrl)  // Forward to the Post Service URI
                )
                .route("notification-service", r -> r
                        .path("/ws/**")
                        .uri(notificationServiceUrl)) // Forward to the Notification Service URI (WebSocket)
                .build();
    }

    @Bean
    public RequestUpgradeStrategy requestUpgradeStrategy() {
        return new ReactorNettyRequestUpgradeStrategy();
    }
}

