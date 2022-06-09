package com.coffeshop.justcoffee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .authorizeRequests((authz) -> authz
                        .mvcMatchers("**.js").permitAll()
                        .mvcMatchers(HttpMethod.GET, "/coffee", "/toppings", "/coffeeOrders/**").permitAll()
                        .mvcMatchers(HttpMethod.POST, "/users", "/coffeeOrders/**", "/coffeeDrinks", "/coffee", "/toppings").permitAll()
                        .mvcMatchers(HttpMethod.DELETE, "/users", "/coffeeOrders", "/orders", "/coffee", "/toppings").permitAll()
//                        .anyRequest().permitAll());
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
//        http.authenticationProvider(new CustomAuthenticationProvider());
//                .authenticationManager(new CustomAuthenticationProvider())
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder noOpPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
