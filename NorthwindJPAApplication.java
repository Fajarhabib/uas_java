/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Ini adalah Main Classnya
package com.northwind.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Rizuki Wahyudi
 */
@SpringBootApplication
@EnableAutoConfiguration
public class NorthwindJPAApplication {

    public static void main(String[] args) {
        SpringApplication.run(NorthwindJPAApplication.class, args);
    }
}
