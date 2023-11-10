package com.glowat.openkkutuweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(it -> it
            .requestMatchers("/actuator/**").authenticated()
            .anyRequest().permitAll())
        .httpBasic(Customizer.withDefaults())
        .csrf(CsrfConfigurer::disable)
        .headers(Customizer.withDefaults());
       //cdn.kkutu.io https://cloudflareinsights.com https://o1271663.ingest.sentry.io https://stats.g.doubleclick.net wss://ws.kkutu.io:* wss://test.kkutu.io:* https://static.kkutu.io https://www.google-analytics.com https://display.ad.daum.net https://aem-ingest.onkakao.net; font-src 'self' https://cdn.kkutu.io; frame-src 'self' https://cdn.kkutu.io https://static.kkutu.io https://t1.daumcdn.net https://www.google.com https://challenges.cloudflare.com https://youtube.com https://www.youtube.com; img-src 'self' 'unsafe-inline' https://cdn.kkutu.io https://www.google-analytics.com data:; manifest-src 'self' https://cdn.kkutu.io; media-src 'self' https://cdn.kkutu.io");
    return http.build();
  }




}
