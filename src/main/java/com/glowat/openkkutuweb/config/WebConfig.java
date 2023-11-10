package com.glowat.openkkutuweb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Value("${spring.cors.pattern}")
  private String pathPattern;

  @Value("#{'${spring.cors.methods}'.split(',')}")
  private String[] methods;

  @Value("#{'${spring.cors.allowed-origins}'.split(',')}")
  private String[] allowedOrigins;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping(pathPattern)
        .allowCredentials(true)
        .allowedMethods(methods)
        .allowedOrigins(allowedOrigins);
  }

}
