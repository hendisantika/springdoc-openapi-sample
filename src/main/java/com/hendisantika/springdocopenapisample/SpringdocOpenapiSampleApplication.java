package com.hendisantika.springdocopenapisample;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringdocOpenapiSampleApplication {
    @Autowired

    private ObjectMapper ojectMapper;

    @PostConstruct
    void init() {
        CustomConverter.add(ojectMapper);
        //in future instead of above we should be able to use below
        //will have to wait for a few releases in swagger and springdoc
        //io.swagger.v3.core.jackson.TypeNameResolver.std.setUseFqn(true);
    }


    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption, @Value("${application" +
            "-version}") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("sample application API")
                        .version(appVersion)
                        .description(appDesciption)
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));

    }


    public static void main(String[] args) {
        SpringApplication.run(SpringdocOpenapiSampleApplication.class, args);
    }

}
