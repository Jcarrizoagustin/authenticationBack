package com.example.authenticationBack.security;

import com.example.authenticationBack.jwt.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //TODO: implement beans for security and manage
    @Value("${security.allow.origin}")
    private String allowOrigin;

    @Autowired
    private JWTAuthenticationFilter authenticationFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //TODO: implement spring security configuration
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST,"/api/auth/register","/api/auth/login").permitAll()
                            .requestMatchers(HttpMethod.GET,"/images/**").permitAll()
                            .anyRequest().authenticated();
                })
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration cc = new CorsConfiguration();
        cc.setAllowedHeaders(List.of("Authorization","Content-Type","Authentication"));
        cc.setAllowedOrigins(List.of(allowOrigin));
        cc.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        cc.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource url = new UrlBasedCorsConfigurationSource();
        url.registerCorsConfiguration("/**",cc);
        return url;
    }


}
