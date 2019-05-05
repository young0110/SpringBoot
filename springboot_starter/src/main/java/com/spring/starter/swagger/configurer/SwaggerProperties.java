package com.spring.starter.swagger.configurer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    private String title = "A SpringBoot project";
    private String description = "Swagger for project";
    private Boolean enable = false;
    private String host;
    private String basePackage;

}
