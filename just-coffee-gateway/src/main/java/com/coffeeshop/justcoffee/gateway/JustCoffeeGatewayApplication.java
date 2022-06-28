package com.coffeeshop.justcoffee.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@SpringBootApplication
public class JustCoffeeGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustCoffeeGatewayApplication.class, args);
	}

//	@Bean
//	SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception {
//
//		return http
//				.authorizeExchange()
//				.pathMatchers(HttpMethod.OPTIONS).permitAll()
//				.anyExchange()
//				.permitAll()
//				.and()
//				.csrf().disable().cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
//				.and()
//				.build();
//	}
}
