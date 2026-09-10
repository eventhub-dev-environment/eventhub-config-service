package com.eventhub.config_service.domain.exception;

public class TenantNotFoundException extends RuntimeException {
    public TenantNotFoundException(String tenantId) {
        super("Configuración no encontrada para el tenant: " + tenantId);
    }
}