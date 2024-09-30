package io.github.robertoaraujo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.github.robertoaraujo.rest.controller"))  // Ajuste para o pacote correto
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(bearerToken()))  // Configura o token JWT
                .securityContexts(Arrays.asList(securityContext()));  // Configura o contexto de segurança
    }

    // Informações da API
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Financeiro - API")
                .description("API modelo RAM")
                .version("1.0.0")
                .contact(new Contact(
                        "Roberto A Moraes",
                        "http://github.com/RobertoAraujo",
                        "endriosrobert@gmail.com"))
                .build();
    }

    // Configuração do esquema de segurança para JWT
    private SecurityScheme bearerToken() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    // Configuração do contexto de segurança
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())  // Referências de segurança
                .build();
    }

    // Configuração das permissões de autenticação para JWT
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

    // Se não houver necessidade de múltiplos recursos Swagger, esse método pode ser removido ou simplificado
    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        return () -> {
            List<SwaggerResource> resources = new ArrayList<>();
            resources.add(swaggerResource("Local", "/v2/api-docs", "1.0.0"));  // Ajuste conforme necessário
            return resources;
        };
    }

    // Método para criar o recurso Swagger
    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}