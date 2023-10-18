package com.treinaRecife.BlogAPI.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("Blog-API")
                .pathsToMatch("/**")
                .build();

    }

    @Bean
    public OpenAPI blogAPI() {
        return new OpenAPI()
                .info(new Info().title("Blog-API")
                        .description("OpenAPI documentation for a Blog")
                        .version("v0.0.1")
                        .contact(new Contact().url("https://www.linkedin.com/in/davi-silva-b91211271/").name("Davi Silva Alves").email("dsaodev@gmail.com"))
                        .license(new License().name("License").url("https://springdoc.org")));

    }

}
