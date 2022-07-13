package com.coffeeshop.justcoffee.menu.options.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .authorizeRequests(authz -> authz
                        .mvcMatchers("**.js").permitAll()
                        .mvcMatchers(HttpMethod.GET, "/coffee", "/toppings").permitAll()
                        .mvcMatchers(HttpMethod.POST, "/coffee", "/toppings").permitAll()
                        .mvcMatchers(HttpMethod.DELETE, "/coffee", "/toppings").permitAll()
                        .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
