package com.eventhub.config_service.infrastructure.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TenantConfigAccessPolicy {

    public void requireTenantAdmin(String tenantId, String activeTenantId, String tenantRoles, String userRoles) {
        if (parseRoles(userRoles).contains("PLATFORM_ADMIN")) {
            return;
        }
        Set<String> roles = parseRoles(tenantRoles);
        if (tenantId == null || !tenantId.equals(activeTenantId)
                || !roles.contains("TENANT_ADMIN")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Se requiere TENANT_ADMIN del tenant");
        }
    }

    private Set<String> parseRoles(String roles) {
        if (roles == null || roles.isBlank()) {
            return Set.of();
        }
        return Arrays.stream(roles.split(","))
                .map(String::trim)
                .filter(role -> !role.isEmpty())
                .collect(Collectors.toSet());
    }
}
