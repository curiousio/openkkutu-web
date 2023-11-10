package com.glowat.openkkutuweb.minify;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinifyConfig {

  @Bean
  public FilterRegistrationBean<?> filterRegistrationBean() {
    return new FilterRegistrationBean<>(new MinifyFilter());
  }

}
