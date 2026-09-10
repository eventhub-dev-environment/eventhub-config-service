package com.eventhub.config_service.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI configServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("EventHub - Config Service API")
                        .description("Servicio de gestión de configuraciones, branding y esquemas de formularios (formSchema) por tenant.")
                        .version("v1.0.0"));
    }
}