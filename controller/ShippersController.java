/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.northwind.jpa.controller;

import com.northwind.jpa.entity.Shippers;
import com.northwind.jpa.entity.response.ApiResponse;
import com.northwind.jpa.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.print.resources.serviceui;

/**
 *
 * @author ahza0
 */      
@RestController
@RequestMapping("/api/v1/shippers")
public class ShippersController {
    @Autowired
    private ShipperService service;
    
    @GetMapping("")
    public ApiResponse getAll(){
        return ApiResponse.ok(service.getAll());
    }
    
    @GetMapping("/{id}")
    public ApiResponse getByID(@PathVariable("id") String id) {
        Shippers cus = service.getById(id);
        if (cus == null) {
            return ApiResponse.notFound();
        }
        return ApiResponse.ok(cus);
    }
    
    @PostMapping("")
    public ApiResponse create(@RequestBody Shippers shipper) {
        if (service.getById(shipper.getShipperID()) != null) {
            return ApiResponse.conflict("Data conflict!");
        }
        service.create(shipper);
        return ApiResponse.created("Create data success!");
    }

    @PutMapping("")
    public ApiResponse update(@RequestBody Shippers shippers) {
        if (service.getById(shippers.getShipperID()) == null) {
            return ApiResponse.notFound("Data not found");
        }
        service.update(shippers);
        return ApiResponse.ok("Update data success!");
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable("id") String id) {
        if (service.getById(id) == null) {
            return ApiResponse.notFound("Data not found");
        }
        service.delete(id);
        return ApiResponse.ok("Delete data success!");
    }
}
