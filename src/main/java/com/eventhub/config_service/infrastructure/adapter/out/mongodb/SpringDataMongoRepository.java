package com.eventhub.config_service.infrastructure.adapter.out.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SpringDataMongoRepository extends MongoRepository<TenantConfigDocument, String> {
    Optional<TenantConfigDocument> findByTenantId(String tenantId);
}