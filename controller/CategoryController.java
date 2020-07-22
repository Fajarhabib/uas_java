/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.northwind.jpa.controller;

import com.northwind.jpa.entity.Categories;
import com.northwind.jpa.entity.response.ApiResponse;
import com.northwind.jpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
// RestController memberi tau springboot untuk menajalankan class CustomerController 
@RestController
// Memberikan request Mapping URL untuk bs mengakses table customers
@RequestMapping("/api/v1/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService service;
    
    // Untuk fungsi resource wajib mempunyai minimal 5 fungsi resource    
    @GetMapping("")
    // Fungsi GetAll untuk mengambil semua data    
    public ApiResponse getAll(
            @RequestParam(defaultValue = "1") int page, 
            @RequestParam(defaultValue = "10") int pageSize,  
            @RequestParam(defaultValue = "", required = false) String search,
            @RequestParam(defaultValue = "customerID", required = false) String sort,
            @RequestParam(defaultValue = "true", required = false) boolean asc) {
        String param = "%" + search + "%";
        System.out.println("param: " + param);
        Page<Categories> result = service.getByPage(page - 1, pageSize);
        return ApiResponse.ok(result);
    }
    
    @GetMapping("/{id}")
    // Fungsi getById untuk mengambil satu data    
    public ApiResponse getByID(@PathVariable("id") String id) {
        Categories cus = service.getById(id);
        if (cus == null) {
            return ApiResponse.notFound();
        }
        return ApiResponse.ok(cus);
    }
    
    @PostMapping("")
    // Create untuk melakukan insert    
    public ApiResponse create(@RequestBody Categories cus) {
        if (service.getById(cus.getCategoryID()) != null) {
            return ApiResponse.conflict("Data conflict!");
        }
        service.create(cus);
        return ApiResponse.created("Create data success!");
    }

    @PutMapping("")
    // Update untuk melakukan edit     
    public ApiResponse update(@RequestBody Categories cus) {
        if (service.getById(cus.getCategoryID()) == null) {
            return ApiResponse.notFound("Data not found");
        }
        service.update(cus);
        return ApiResponse.ok("Update data success!");
    }

    @DeleteMapping("/{id}")
    // Delete untuk melakukan hapus    
    public ApiResponse delete(@PathVariable("id") String id) {
        if (service.getById(id) == null) {
            return ApiResponse.notFound("Data not found");
        }
        service.delete(id);
        return ApiResponse.ok("Delete data success!");
    }
}
