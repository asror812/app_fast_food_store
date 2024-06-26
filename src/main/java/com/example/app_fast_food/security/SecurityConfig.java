package com.example.app_fast_food.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity , JwtAuthorizationFilter authorizationFilter)
            throws Exception {
       return  httpSecurity
                 .csrf(AbstractHttpConfigurer::disable)
                 .cors(AbstractHttpConfigurer::disable)
                 .authorizeHttpRequests(
                        registry -> registry
                                .requestMatchers("/auth/**" ,
                                        "/v3/api-docs",
                                        "/swagger-resources/**",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html",
                                        "/v3/api-docs/**")
                                .permitAll()
                 )
                 .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 )
                 .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                 .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
