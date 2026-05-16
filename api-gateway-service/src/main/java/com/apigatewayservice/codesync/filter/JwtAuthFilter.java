package com.apigatewayservice.codesync.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.util.Objects;

@Component
public class JwtAuthFilter extends AbstractGatewayFilterFactory<JwtAuthFilter.Config> {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    public JwtAuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> filterRequest(exchange, chain, config);
    }

    private Mono<Void> filterRequest(ServerWebExchange exchange, GatewayFilterChain chain, Config config) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();

        String token = extractBearerToken(request);
        if (token == null) {
            log.warn("Missing or malformed Authorization header for path: {}", path);
            return onUnauthorized(exchange, "Missing or malformed Authorization header");
        }

        Claims claims;
        try {
            claims = parseToken(token);
        } catch (JwtException error) {
            return handleTokenError(exchange, path, error);
        }

        Number userIdNumber = claims.get("userId", Number.class);
        if (userIdNumber == null) {
            log.warn("JWT missing 'userId' claim for path: {}", path);
            return onUnauthorized(exchange, "Token missing required claims");
        }

        UserIdentity identity = new UserIdentity(
                String.valueOf(userIdNumber.longValue()),
                claims.get("role", String.class),
                claims.getSubject()
        );

        ServerHttpRequest forwardedRequest = enrichRequest(request, config, identity);
        log.debug("JWT valid - userId={} role={} path={}", identity.userId(), identity.userRole(), path);
        return chain.filter(exchange.mutate().request(forwardedRequest).build());
    }

    private String extractBearerToken(ServerHttpRequest request) {
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.substring(7);
    }

    private Mono<Void> handleTokenError(ServerWebExchange exchange, String path, JwtException error) {
        if (error instanceof ExpiredJwtException) {
            log.warn("JWT expired for path: {} - {}", path, error.getMessage());
            return onUnauthorized(exchange, "Token has expired");
        }
        if (error instanceof MalformedJwtException) {
            log.warn("Malformed JWT for path: {} - {}", path, error.getMessage());
            return onUnauthorized(exchange, "Malformed token");
        }

        log.warn("Invalid JWT for path: {} - {}", path, error.getMessage());
        return onUnauthorized(exchange, "Invalid token");
    }

    private ServerHttpRequest enrichRequest(ServerHttpRequest request, Config config, UserIdentity identity) {
        if (!config.isForwardIdentityHeaders()) {
            return request;
        }
        return request.mutate()
                .header("X-User-Id", identity.userId())
                .header("X-User-Role", Objects.requireNonNullElse(identity.userRole(), ""))
                .header("X-User-Email", Objects.requireNonNullElse(identity.userEmail(), ""))
                .build();
    }

    private Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Mono<Void> onUnauthorized(ServerWebExchange exchange, String reason) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("X-Auth-Error", reason);
        return response.setComplete();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public static class Config {
        private boolean forwardIdentityHeaders = true;

        public boolean isForwardIdentityHeaders() {
            return forwardIdentityHeaders;
        }

        public void setForwardIdentityHeaders(boolean forwardIdentityHeaders) {
            this.forwardIdentityHeaders = forwardIdentityHeaders;
        }
    }

    private record UserIdentity(String userId, String userRole, String userEmail) {
    }
}
