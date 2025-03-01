package br.com.yandeara.voting.application.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Api de Votação de Associados")
                .description("Api criada para que associados possam cadastrar e votar em pautas")
                .version("1.0"));
    }
}
