package com.eventhub.config_service.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomField {

    @Schema(description = "Identificador único del campo en el formulario", example = "tShirtSize")
    private String key;

    @Schema(description = "Nombre o pregunta del campo", example = "Talla de Camiseta")
    private String label;

    @Schema(description = "Tipo de dato (TEXT, NUMBER, ENUM, BOOLEAN)", example = "ENUM")
    private String type;

    @Schema(description = "Indica si el campo es obligatorio", example = "true")
    private boolean required;

    private List<FieldOption> options;
}