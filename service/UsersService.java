/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.northwind.jpa.service;

import com.northwind.jpa.entity.Products;
import com.northwind.jpa.entity.Users;
import com.northwind.jpa.repository.ProductsRepository;
import com.northwind.jpa.repository.UsersRepository;
import java.util.List;
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
public class UsersService implements RepoService<Users>{
    
     @Resource
    private UsersRepository repo;
     

    @Override
    public Users create(Users obj) {
         Users user = repo.save(obj);
        return user;
    }

    @Override
    public Users update(Users obj) {
         
        Users user = repo.findById(obj.getUserID()).orElse(null);
        if(user != null) {
            user.setName(obj.getName());
            user.setTitle(obj.getTitle());
            user.setBirthdate(obj.getBirthdate());
            user.setHiredate(obj.getHiredate());
            user.setPosition(obj.getPosition());
            user.setEmail(obj.getEmail());
            user.setPassword(obj.getPassword());
            
//        Simpan perubahannya
            repo.flush();
            return user;
        }else{
            throw new NoSuchElementException("Data tidak ditemukan!");
        }
    }

    @Override
    public Users delete(Object id) {
        Users user = getById(id);
        if(user != null){
            repo.delete(user);
            return user;
        }else{
            throw new NoSuchElementException("Data tidak ditemukan!");
        }
    }

    @Override
    public Users getById(Object id) {
          return repo.findById(Integer.valueOf(id.toString())).orElse(null);
    }

    @Override
    public List<Users> getAll() {
        return repo.findAll();
    }
    
     public Page<Users> getByPage(int page, int pageSize, String Name, String sort, boolean asc) {
        Sort sortBy = Sort.by(sort);
        sortBy = asc ? sortBy.ascending() : sortBy.descending();
        Pageable pageable = PageRequest.of(page, pageSize, sortBy);
        return repo.findByNameLike(Name, pageable);
    }
    
}
