/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.northwind.jpa.repository;

import com.northwind.jpa.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
// Repository harus pake interface
public interface CategoriesRepository extends JpaRepository<Categories, Integer>{
    
}
