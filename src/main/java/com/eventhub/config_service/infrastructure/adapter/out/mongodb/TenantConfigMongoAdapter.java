package com.eventhub.config_service.infrastructure.adapter.out.mongodb;

import com.eventhub.config_service.domain.model.TenantConfig;
import com.eventhub.config_service.domain.port.out.TenantConfigRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TenantConfigMongoAdapter implements TenantConfigRepositoryPort {

    private final SpringDataMongoRepository mongoRepository;

    @Override
    public TenantConfig save(TenantConfig tenantConfig) {
        TenantConfigDocument document = toDocument(tenantConfig);
        TenantConfigDocument saved = mongoRepository.save(document);
        return toDomain(saved);
    }

    @Override
    public Optional<TenantConfig> findByTenantId(String tenantId) {
        return mongoRepository.findByTenantId(tenantId)
                .map(this::toDomain);
    }

    private TenantConfigDocument toDocument(TenantConfig domain) {
        return TenantConfigDocument.builder()
                .id(domain.getId())
                .tenantId(domain.getTenantId())
                .brandName(domain.getBrandName())
                .logoUrl(domain.getLogoUrl())
                .primaryColor(domain.getPrimaryColor())
                .secondaryColor(domain.getSecondaryColor())
                .transactionCommission(domain.getTransactionCommission())
                .formSchema(domain.getFormSchema())
                .build();
    }

    private TenantConfig toDomain(TenantConfigDocument document) {
        return TenantConfig.builder()
                .id(document.getId())
                .tenantId(document.getTenantId())
                .brandName(document.getBrandName())
                .logoUrl(document.getLogoUrl())
                .primaryColor(document.getPrimaryColor())
                .secondaryColor(document.getSecondaryColor())
                .transactionCommission(document.getTransactionCommission())
                .formSchema(document.getFormSchema())
                .build();
    }
}