/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.northwind.jpa.service;

import com.northwind.jpa.entity.Categories;
import com.northwind.jpa.entity.Customers;
import com.northwind.jpa.repository.CategoriesRepository;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class CategoryService implements RepoService<Categories>{
    @Resource
    private CategoriesRepository repo;
    
    @Override
    public Categories create(Categories obj) {
        Categories cus = repo.save(obj);
        return cus;
    }

    @Override
    public Categories update(Categories obj) {
        Categories category = repo.findById(obj.getCategoryID()).orElse(null);
        if (category != null) { 
            category.setCategoryName(obj.getCategoryName());
            repo.flush();
            return obj;
        } else {
            throw new NoSuchElementException("Data not found!");
        }
    }

    @Override
    public Categories delete(Object id) {
        Categories category = repo.findById(Integer.valueOf(id.toString())).orElse(null);
        if (category != null) {
            repo.delete(category);
            return category;
        } else {
            throw new NoSuchElementException("Data not found!");
        }
    }

    @Override
    public Categories getById(Object id) {
        Categories category = repo.findById(Integer.valueOf(id.toString())).orElse(null);
        return category;
    }

    @Override
    public List<Categories> getAll() {
        return repo.findAll();
    }
    
    public Page<Categories> getByPage(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return repo.findAll(pageable);
    }
}
