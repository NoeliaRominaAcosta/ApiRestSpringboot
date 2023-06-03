package com.itrsa.monolith.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Proyecto base",
                description = "" +
                        "Se publica API Rest ")
)
@Configuration
public class SpringFoxConfig {

}
