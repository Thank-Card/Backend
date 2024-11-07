package com.thankscard.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.models.GroupedOpenApi;


@OpenAPIDefinition(
        info = @Info(title = "Opening", description = "Opening API Docs", version = "v1"),
        servers = {

                @Server(url = "http://localhost:8080", description = "로컬 URL"),
                @Server(url = "http://3.38.151.193:8080", description = "서버 URL"),
        }

)
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi SwaggerOpenApi() {

        return GroupedOpenApi.builder()
                .group("Swagger-api")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public OpenAPI api() {
        SecurityScheme apiKey = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .name("Authorization")
                .scheme("bearer")
                .bearerFormat("JWT");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Bearer Token");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Bearer Token", apiKey))
                .addSecurityItem(securityRequirement);
    }
}