package com.hendisantika.springdocopenapisample;

import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringdocOpenapiSampleApplication {

    @PostConstruct
    void init() {
        // swagger-core still relies on Jackson 2, while Spring Boot 4 ships Jackson 3 by default
        CustomConverter.add(Json.mapper());
        //in future instead of above we should be able to use below
        //will have to wait for a few releases in swagger and springdoc
        //io.swagger.v3.core.jackson.TypeNameResolver.std.setUseFqn(true);
    }


    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String appDescription, @Value("${application" +
            "-version}") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("sample application API")
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));

    }


    public static void main(String[] args) {
        SpringApplication.run(SpringdocOpenapiSampleApplication.class, args);
    }

}
