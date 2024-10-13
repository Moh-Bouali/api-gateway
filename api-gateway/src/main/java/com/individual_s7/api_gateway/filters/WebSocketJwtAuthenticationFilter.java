//package com.individual_s7.api_gateway.filters;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.ReactiveAuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.server.context.ServerSecurityContextRepository;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import org.springframework.web.util.UriComponentsBuilder;
//import reactor.core.publisher.Mono;
//
//@Component
//public class WebSocketJwtAuthenticationFilter implements WebFilter {
//
//    private final ReactiveJwtDecoder jwtDecoder;
//    private final ServerSecurityContextRepository securityContextRepository;
//
//    @Autowired
//    public WebSocketJwtAuthenticationFilter(ReactiveJwtDecoder jwtDecoder, ServerSecurityContextRepository securityContextRepository) {
//        this.jwtDecoder = jwtDecoder;
//        this.securityContextRepository = securityContextRepository;
//    }
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        if (exchange.getRequest().getURI().getPath().startsWith("/ws")) {
//            String token = UriComponentsBuilder.fromUri(exchange.getRequest().getURI())
//                    .build()
//                    .getQueryParams()
//                    .getFirst("token");
//
//            if (token != null) {
//                return jwtDecoder.decode(token)
//                        .flatMap(jwt -> {
//                            Authentication authToken = new JwtAuthenticationToken(jwt); // Create auth token from decoded JWT
//                            SecurityContext context = SecurityContextHolder.createEmptyContext();
//                            context.setAuthentication(authToken); // Set the authentication in the security context
//                            return securityContextRepository.save(exchange, context) // Save the security context
//                                    .then(chain.filter(exchange)); // Proceed with the filter chain
//                        })
//                        .onErrorResume(e -> Mono.error(new IllegalStateException("Invalid JWT token")));
//            } else {
//                return Mono.error(new IllegalStateException("Missing JWT token"));
//            }
//        }
//
//        return chain.filter(exchange);
//    }
//}
//
