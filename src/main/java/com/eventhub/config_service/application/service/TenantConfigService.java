package com.eventhub.config_service.application.service;


import com.eventhub.config_service.domain.exception.TenantNotFoundException;
import com.eventhub.config_service.domain.model.TenantConfig;
import com.eventhub.config_service.domain.port.in.GetTenantConfigUseCase;
import com.eventhub.config_service.domain.port.out.TenantConfigRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TenantConfigService implements GetTenantConfigUseCase {

    private final TenantConfigRepositoryPort repositoryPort;

    @Override
    public TenantConfig getConfigByTenantId(String tenantId) {
        return repositoryPort.findByTenantId(tenantId)
                .orElseThrow(() -> new TenantNotFoundException(tenantId));
    }

    @Override
    public TenantConfig saveTenantConfig(TenantConfig tenantConfig) {
        return repositoryPort.save(tenantConfig);
    }
}