package com.youngc.redis.config;

import com.youngc.redis.config.component.AuthTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import java.util.Arrays;

/**
 * 跨域
 */
@Configuration
public class CorsConfig extends WebMvcConfigurationSupport {

  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("*") //host
            .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS");
  }

  @Override
  protected void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new AuthTokenInterceptor()).addPathPatterns("/api/**").excludePathPatterns(Arrays.asList("/api/common/*", "/api/public/*"));
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

}