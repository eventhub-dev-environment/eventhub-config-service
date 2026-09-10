package com.eventhub.config_service.domain.port.out;

import com.eventhub.config_service.domain.model.TenantConfig;

import java.util.Optional;

public interface TenantConfigRepositoryPort {
    TenantConfig save(TenantConfig tenantConfig);
    Optional<TenantConfig> findByTenantId(String tenantId);
}