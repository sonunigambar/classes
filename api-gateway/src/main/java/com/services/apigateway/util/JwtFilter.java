package com.services.apigateway.util;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
public class JwtFilter implements GlobalFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        // ✅ Skip auth APIs
        if (path.contains("/auth")) {
            return chain.filter(exchange);
        }

        // 1. Get token
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);
        ServerHttpRequest modifiedRequest = null;
        // 2. Validate token
        try {
            String username = jwtUtil.validateToken(token); //sonu

            // 3. Add user info to header
            modifiedRequest = exchange.getRequest()
                    .mutate()
                    .header("X-User", username)
                    .build();
        }catch (ExpiredJwtException ex){
            return handleError(exchange, "Token Expired", HttpStatus.UNAUTHORIZED);
        }
        return chain.filter(exchange.mutate().request(modifiedRequest).build());
    }

    private Mono<Void> handleError(ServerWebExchange exchange, String message, HttpStatus status) {

        ServerHttpResponse response = exchange.getResponse();

        response.setStatusCode(status);

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        System.out.println(message);
        String body = String.format("""
                "error": "%s",
                "timestamp":"%s",
                "status": %s
                """, message, new Date(), status);

        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes());

        return response.writeWith(Mono.just(buffer));

    }
}
