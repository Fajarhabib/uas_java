/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.northwind.jpa.service;

import com.northwind.jpa.entity.Customers;
import com.northwind.jpa.repository.CustomersRepository;
import java.util.List;
import java.util.NoSuchElementException;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author ahza0
 */
// CustomerService itu akan dipakai didalam Controller
// Anotasi Service agar springboot melakukan scan untuk dimasukkan kedalam contex
// Dengan implementasi DI, atau Dependecies Injection
@Service
public class CustomerService implements RepoService<Customers>{
    @PersistenceContext
    private EntityManager em;
    // Anotasi untuk menginject tanpa harus menggunakan constructor
    @Resource
    private CustomersRepository repo;

    @Override
    public Customers create(Customers obj) {
        Customers cus = repo.save(obj);
        return cus;
    }

    @Override
    public Customers update(Customers obj) {
        Customers cus = repo.findById(obj.getCustomerID()).orElse(null);
        if (cus != null) { 
            cus.setAddress(obj.getAddress());
            cus.setCity(obj.getCity());
            cus.setCompanyName(obj.getCompanyName());
            cus.setContactName(obj.getContactName());
            cus.setContactTitle(obj.getContactTitle());
            cus.setCountry(obj.getCountry());
            cus.setFax(obj.getFax());
            cus.setPhone(obj.getPhone());
            cus.setPostalCode(obj.getPostalCode());
            cus.setRegion(obj.getRegion());
            repo.flush();
            return obj;
        } else {
            throw new NoSuchElementException("Data not found!");
        }
    }

    @Override
    public Customers delete(Object id) {
        Customers cus = repo.findById(id.toString()).orElse(null);
        if (cus != null) {
            repo.delete(cus);
            return cus;
        } else {
            throw new NoSuchElementException("Data not found!");
        }
    }

    @Override
    public Customers getById(Object id) {
        Customers cus = repo.findById(id.toString()).orElse(null);
        return cus;
    }

    @Override
    public List<Customers> getAll() {
        return repo.findAll();
    }
    
    public Page<Customers> getByPage(int page, int pageSize, String companyName, String sort, boolean asc) {
        Sort sortBy = asc ? Sort.by(sort).ascending() : Sort.by(sort).descending();
        Pageable pageable = PageRequest.of(page, pageSize, sortBy);
        return repo.findByCompanyNameLike(companyName, pageable);
    }
}
