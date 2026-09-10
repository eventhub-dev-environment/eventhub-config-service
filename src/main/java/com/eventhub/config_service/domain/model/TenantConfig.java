package com.eventhub.config_service.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenantConfig {

    @Schema(description = "Identificador autogenerado por MongoDB", accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    @NotBlank(message = "El tenantId es obligatorio")
    @Size(min = 3, max = 50, message = "El tenantId debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[a-z0-9-]+$", message = "El tenantId solo puede contener letras minúsculas, números y guiones")
    @Schema(description = "Identificador único del tenant", example = "carrera-tulua-2026")
    private String tenantId;

    @NotBlank(message = "El nombre de la marca es obligatorio")
    @Schema(description = "Nombre comercial o de la marca", example = "Media Maratón Tuluá")
    private String brandName;

    @Schema(description = "URL del logotipo del tenant", example = "http://localhost:9000/branding/logo.png")
    private String logoUrl;

    @Pattern(regexp = "^#([A-Fa-f0-9]{6})$", message = "El color primario debe ser un código HEX válido (ej. #0055FF)")
    @Schema(description = "Color primario en formato HEX", example = "#0055FF")
    private String primaryColor;

    @Pattern(regexp = "^#([A-Fa-f0-9]{6})$", message = "El color secundario debe ser un código HEX válido (ej. #FF3300)")
    @Schema(description = "Color secundario en formato HEX", example = "#FF3300")
    private String secondaryColor;

    @NotNull(message = "La comisión es obligatoria")
    @PositiveOrZero(message = "La comisión no puede ser negativa")
    @Max(value = 100, message = "La comisión no puede superar el 100%")
    @Schema(description = "Porcentaje de comisión por transacción", example = "4.5")
    private Double transactionCommission;

    @Valid
    private List<CustomField> formSchema;
}