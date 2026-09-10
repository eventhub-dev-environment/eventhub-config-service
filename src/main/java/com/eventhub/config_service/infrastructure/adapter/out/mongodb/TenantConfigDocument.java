package com.eventhub.config_service.infrastructure.adapter.out.mongodb;

import com.eventhub.config_service.domain.model.CustomField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tenant_configs")
public class TenantConfigDocument {
    @Id
    private String id;

    @Indexed(unique = true)
    private String tenantId;

    private String brandName;
    private String logoUrl;
    private String primaryColor;
    private String secondaryColor;
    private Double transactionCommission;
    private List<CustomField> formSchema;
}