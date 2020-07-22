/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.northwind.jpa.config;

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author ahza0
 */
// Anotasi Configuration sebagai class yang akan di scann untuk menjalankan konfigurasi
@Configuration
public class AppConfiguration {
    // Anotasi Bean adalah class yang akan di jalankan    
    @Bean
    // cors = cross origin resource sharing adalah config agar supaya program bisa di request oleh domain berbeda    
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedHeaders("Authorization, Content-Type, Accept, x-requested-with, Cache-Control")
                        .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")
                        .maxAge(3600);
            }
        };
    }
    
    @Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
    // dispatcherServlet = diset untuk keperluan server    
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setDispatchOptionsRequest(true);
        return dispatcherServlet;
    }
}
