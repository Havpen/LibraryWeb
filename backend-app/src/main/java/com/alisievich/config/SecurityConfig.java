package com.alisievich.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.GET, "/api/v1/furniture-templates**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/furniture-templates/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/furniture**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/furniture/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/materials**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/materials/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/material-categories**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/material-categories/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/environmentMaps**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/environmentMaps/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/companies**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/companies/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/scenes/*/resources/furniture").permitAll() // TODO: do not forget about permissions
                        .requestMatchers(HttpMethod.GET, "/api/v1/scenes/*/resources/materials").permitAll() // TODO: do not forget about permissions
                        .requestMatchers(HttpMethod.GET, "/api/v1/scenes/*/resources/furnitureTemplatePresets").permitAll() // TODO: do not forget about permissions
                        .requestMatchers("/api/v1/issues/**").permitAll()
                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers("/ws-channel/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }
}
