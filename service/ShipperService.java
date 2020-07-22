/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.northwind.jpa.service;

import com.northwind.jpa.entity.Shippers;
import com.northwind.jpa.repository.ShippersRepository;
import java.util.List;
import java.util.NoSuchElementException;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ahza0
 */
@Service
public class ShipperService implements RepoService<Shippers>{
    
    @Resource
    private ShippersRepository repo;
    
    @Override
    public Shippers create(Shippers obj) {
        Shippers result = repo.save(obj);
        return result;
    }

    @Override
    public Shippers update(Shippers obj) {
        Shippers old = repo.findById(obj.getShipperID()).orElse(null);
        if(old != null){
            old.setCompanyName(obj.getCompanyName());
            old.setPhone(obj.getPhone());
            repo.flush();
            return obj;
        } else {
            throw new NoSuchElementException("Data not found!");
        }
    }

    @Override
    public Shippers delete(Object id) {
        Shippers shipper = repo.findById(Integer.valueOf(id.toString())).orElse(null);
        if(shipper != null){
            repo.delete(shipper);
            return shipper;
        }else{
            throw new NoSuchElementException("Data not found!");
        }
    }

    @Override
    public Shippers getById(Object id) {
        Shippers shipper = repo.findById(Integer.valueOf(id.toString())).orElse(null);
        return shipper;
    }

    @Override
    public List<Shippers> getAll() {
        return repo.findAll();
    }
    
}
