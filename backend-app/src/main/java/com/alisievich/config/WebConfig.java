package com.alisievich.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final String[] allowedOrigins;

    public WebConfig(@Value("${cors.allowed-origins}") String[] allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (allowedOrigins.length == 1 && "*".equals(allowedOrigins[0])) {
            registry.addMapping("/**").allowedMethods("*");
        } else {
            registry.addMapping("/**").allowedMethods("*").allowedOrigins(allowedOrigins).allowCredentials(true);
        }
    }
}
