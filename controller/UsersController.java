/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.northwind.jpa.controller;

import com.northwind.jpa.entity.Products;
import com.northwind.jpa.entity.Users;
import com.northwind.jpa.entity.response.ApiResponse;
import com.northwind.jpa.service.ProductsService;
import com.northwind.jpa.service.UsersService;
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
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    
    @Autowired
    private UsersService service;
    
    @GetMapping("")
    public ApiResponse getAll(
            @RequestParam(defaultValue = "1") int page, 
            @RequestParam(defaultValue = "10") int pageSize, 
            @RequestParam(defaultValue = "", required = false) String search, 
            @RequestParam(defaultValue = "UserID", required = false) String sort, 
            @RequestParam(defaultValue = "true", required = false) boolean asc){
        try {
            String param = "%" + search + "%";
            Page<Users> result = service.getByPage(page - 1, pageSize, param, sort, asc);
            return ApiResponse.ok(result);
        } catch (Exception e) {
            return ApiResponse.internalServerError(e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable("id") int id){
        try {
            Users user = service.getById(id);
            if(user == null) {
                return ApiResponse.notFound("Data tidak ditemukan!");
            }
            return ApiResponse.ok(user);
        } catch (Exception e) {
            return ApiResponse.internalServerError(e.getMessage());
        }
    }
    
    @PostMapping("")
    public ApiResponse create(@RequestBody Users user){
        try {
            Users result = service.create(user);
            return ApiResponse.created("Create Users Success!");
        } catch (Exception e) {
            return ApiResponse.internalServerError(e.getMessage());
        }
    }
    
    @PutMapping("")
    public ApiResponse update(@RequestBody Users user){
        try {
            Users result = service.update(user);
            return ApiResponse.accepted("Update Users Success!");
        } catch (Exception e) {
            return ApiResponse.internalServerError(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable("id") int id){
        try {
            Users result = service.delete(id);
            return ApiResponse.ok("Delete Users Success!");
        } catch (Exception e) {
            return ApiResponse.internalServerError(e.getMessage());
        }
    }
}
