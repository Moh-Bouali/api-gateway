//package com.individual_s7.api_gateway.routes;
//
//import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
//import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.function.RequestPredicates;
//import org.springframework.web.servlet.function.RouterFunction;
//import org.springframework.web.servlet.function.ServerResponse;
//
//@Configuration
//public class Routes {
//    @Bean
//    public RouterFunction<ServerResponse> userServiceRoute() {
//        return GatewayRouterFunctions.route("user_service")
//                .route(RequestPredicates.path("api/user"), HandlerFunctions.http("http://localhost:8080"))
//                .build();
//    }
//}

//package com.individual_s7.api_gateway.routes;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class Routes {
//
//    @Bean
//    public RouteLocator userServiceRoute(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("user_service", r -> r.path("/api/user/**") // Ensure this matches the paths you want to route
//                        .uri("http://localhost:8080")) // Forward to your user service
//                .build();
//    }
//}

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
                        .path("/api/user/register")  // Predicate for matching the registration path
                        .uri("http://localhost:8080")  // Forward to the User Service URI
                )
                // Route for all other user service calls that require authentication
                .route("user-service", r -> r
                        .path("/api/user/**")  // Predicate for matching the path
                        .filters(f -> f.removeRequestHeader("Authorization")) // Remove Authorization header
                        .uri("http://localhost:8080")  // Forward to the User Service URI
                )
                .build();
    }
}

