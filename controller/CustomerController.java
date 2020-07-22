/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// File controller ini adalah yang akan muncul di browser sebagai resources API backend
package com.northwind.jpa.controller;

import com.northwind.jpa.entity.Customers;
import com.northwind.jpa.entity.response.ApiResponse;
import com.northwind.jpa.service.CustomerService;
import java.util.List;
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
 * @author ahza0
 */
// RestController memberi tau springboot untuk menajalankan class CustomerController 
@RestController
// Memberikan request Mapping URL untuk bs mengakses table customers
@RequestMapping("/api/v1/customers")
public class CustomerController {
    // Untuk memanggil DI didalam contexnya springboot    
    @Autowired
    private CustomerService service;
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
        Page<Customers> result = service.getByPage(page - 1, pageSize, param, sort, asc);
        return ApiResponse.ok(result);
    }
    
    @GetMapping("/{id}")
    // Fungsi getById untuk mengambil satu data    
    public ApiResponse getByID(@PathVariable("id") String id) {
        Customers cus = service.getById(id);
        if (cus == null) {
            return ApiResponse.notFound();
        }
        return ApiResponse.ok(cus);
    }
    
    @PostMapping("")
    // Create untuk melakukan insert    
    public ApiResponse create(@RequestBody Customers cus) {
        if (service.getById(cus.getCustomerID()) != null) {
            return ApiResponse.conflict("Data conflict!");
        }
        service.create(cus);
        return ApiResponse.created("Create data success!");
    }

    @PutMapping("")
    // Update untuk melakukan edit     
    public ApiResponse update(@RequestBody Customers cus) {
        if (service.getById(cus.getCustomerID()) == null) {
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
