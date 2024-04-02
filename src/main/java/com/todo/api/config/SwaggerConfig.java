package com.todo.api.config;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "security", scheme = "bearer")
public class SwaggerConfig {

    @Bean
    public OpenAPI configure() {

        Info info = new Info()
                .title("API TO-DO")
                .version("1.0")
                .description("API TO-DO");

        return new OpenAPI().info(info);
    }


}
