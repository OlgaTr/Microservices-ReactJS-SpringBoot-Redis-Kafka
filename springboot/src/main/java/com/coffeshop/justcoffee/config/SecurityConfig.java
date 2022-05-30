package com.coffeshop.justcoffee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .authorizeRequests((authz) -> authz
                        .mvcMatchers("**.js").permitAll()
                        .mvcMatchers(HttpMethod.GET, "/coffee", "/toppings").permitAll()
                        .mvcMatchers(HttpMethod.POST, "/users").permitAll()
                        .anyRequest().permitAll());
//                        .anyRequest().authenticated());
        return http.build();
    }
}
