package com.spring.demo.config;


import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class JettyConfig {

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
        factory.setPort(9000);
        factory.setContextPath("/home");
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/static/error.html"));
        return factory;
    }
}
