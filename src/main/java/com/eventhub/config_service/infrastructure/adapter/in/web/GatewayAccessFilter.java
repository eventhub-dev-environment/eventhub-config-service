package com.eventhub.config_service.infrastructure.adapter.in.web;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public class GatewayAccessFilter extends OncePerRequestFilter {

    @Value("${gateway.internal-secret}")
    private String expectedSecret;

    // Rutas que no pasan por el gateway: Swagger local y las probes de Kubernetes
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/actuator/health")
                || path.contains("/swagger-ui")
                || path.contains("/v3/api-docs");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String secretHeader = request.getHeader("X-Gateway-Secret");

        if (expectedSecret == null || secretHeader == null || !secretsMatch(secretHeader, expectedSecret)) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write("Acceso denegado: Las peticiones deben pasar obligatoriamente por el API Gateway");
            return;
        }

        filterChain.doFilter(request, response);
    }

    // Comparación en tiempo constante, para no filtrar el secreto por tiempos de respuesta
    private boolean secretsMatch(String provided, String expected) {
        return MessageDigest.isEqual(
                provided.getBytes(StandardCharsets.UTF_8),
                expected.getBytes(StandardCharsets.UTF_8));
    }
}
