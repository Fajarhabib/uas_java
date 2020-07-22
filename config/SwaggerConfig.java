/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// File untuk konfigurasi swagger, ialah si aplikasi Restfull API
package com.northwind.jpa.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author ahza0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public SecurityConfiguration securityInfo(){
        return new SecurityConfiguration(null, null, null, null, "", 
                ApiKeyVehicle.HEADER, "Authorization", "");
    }
    
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Lists.newArrayList(apiKey()));
    }
    
    private ApiKey apiKey(){
        return new ApiKey("Authorization", "Authorization", "header");
    } 
}
