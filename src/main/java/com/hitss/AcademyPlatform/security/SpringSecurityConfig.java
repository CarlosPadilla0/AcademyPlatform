package com.hitss.AcademyPlatform.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.hitss.AcademyPlatform.security.filter.JwtAuthenticationFilter;
import com.hitss.AcademyPlatform.security.filter.JwtValidationFilter;


@Configuration
@EnableMethodSecurity(prePostEnabled = true) 
public class SpringSecurityConfig {
	
    @Autowired
	private AuthenticationConfiguration authenticationConfig;
    

	@Bean
	AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfig.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 private static final String[] SWAGGER_WHITELIST = {
	            "/v3/api-docs/**",
	            "/swagger-ui/**",
	            "/swagger-ui.html"
	    };
    
	 @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http.authorizeHttpRequests(auth -> auth
	                .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
	                .requestMatchers(HttpMethod.POST, "/login").permitAll()
	                .requestMatchers(SWAGGER_WHITELIST).permitAll()
	                .anyRequest().authenticated())
	                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
	                .addFilter(new JwtValidationFilter(authenticationManager()))
	                .csrf(config -> config.disable())
	                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	                .build();
	    }
}