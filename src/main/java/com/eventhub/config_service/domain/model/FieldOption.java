package com.eventhub.config_service.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldOption {

    @Schema(description = "Etiqueta visible para el usuario", example = "Medium")
    private String label;

    @Schema(description = "Valor interno almacenado", example = "M")
    private String value;
}