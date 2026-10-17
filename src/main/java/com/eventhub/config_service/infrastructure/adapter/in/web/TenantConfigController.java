package com.eventhub.config_service.infrastructure.adapter.in.web;

import com.eventhub.config_service.domain.model.TenantConfig;
import com.eventhub.config_service.domain.port.in.GetTenantConfigUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/configs")
@RequiredArgsConstructor
@Tag(name = "Tenant Configuration", description = "Endpoints para la gestión y consulta de la configuración de cada tenant")
public class TenantConfigController {

    private final GetTenantConfigUseCase getTenantConfigUseCase;
    private final TenantConfigAccessPolicy accessPolicy;

    @GetMapping("/{tenantId}")
    @Operation(summary = "Obtener configuración por Tenant ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Configuración encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tenant no encontrado")
    })
    public ResponseEntity<TenantConfig> getConfig(
            @PathVariable String tenantId,
            @RequestHeader(value = "X-Tenant-ID", required = false) String activeTenantId,
            @RequestHeader(value = "X-Tenant-Roles", required = false) String tenantRoles,
            @RequestHeader(value = "X-User-Roles", required = false) String userRoles) {
        accessPolicy.requireTenantAdmin(tenantId, activeTenantId, tenantRoles, userRoles);
        return ResponseEntity.ok(getTenantConfigUseCase.getConfigByTenantId(tenantId));
    }

    @PostMapping
    @Operation(summary = "Guardar o actualizar configuración de un Tenant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Configuración guardada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o faltantes")
    })
    public ResponseEntity<TenantConfig> saveConfig(
            @Valid @RequestBody TenantConfig tenantConfig,
            @RequestHeader(value = "X-Tenant-ID", required = false) String activeTenantId,
            @RequestHeader(value = "X-Tenant-Roles", required = false) String tenantRoles,
            @RequestHeader(value = "X-User-Roles", required = false) String userRoles) {
        accessPolicy.requireTenantAdmin(tenantConfig.getTenantId(), activeTenantId, tenantRoles, userRoles);
        TenantConfig saved = getTenantConfigUseCase.saveTenantConfig(tenantConfig);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}