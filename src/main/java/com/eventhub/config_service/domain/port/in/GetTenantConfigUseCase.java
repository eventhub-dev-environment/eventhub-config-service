package com.eventhub.config_service.domain.port.in;

import com.eventhub.config_service.domain.model.TenantConfig;

public interface GetTenantConfigUseCase {
    TenantConfig getConfigByTenantId(String tenantId);
    TenantConfig saveTenantConfig(TenantConfig tenantConfig);
}