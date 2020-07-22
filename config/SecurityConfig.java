/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// File yang berfungsi untuk mengetahui siapa yang meminta request
package com.northwind.jpa.config;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsUtils;

/**
 *
 * @author ahza0
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    // Security menggunakan jwt, jason web token    
    @Value("${security.oauth2.resource.jwt.key-value}")
    private String secretKey;

    @Value("${security.oauth2.resource.jwt.audience}")
    private String audience;
    
    @Value("${security.oauth2.resource.jwt.issuer}")
    private String issuer; 
    
    @Override
    public void configure(WebSecurity web) throws Exception {         
//        web.ignoring().requestMatchers(CorsUtils::isPreFlightRequest).antMatchers("/api/v1/auth","/api/v1/auth/*"); 
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception { 
        
        http = http.authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/api/v1/auth","/api/v1/swagger-ui.html").permitAll()
//                .antMatchers("/api/v1/**").fullyAuthenticated()
                .and()
                .addFilterBefore(new WebSecurityCorsFilter(), ChannelProcessingFilter.class);
        
        JwtWebSecurityConfigurer.forHS256(audience, issuer, secretKey.getBytes()).configure(http); 
    }
}
