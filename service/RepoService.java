/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.northwind.jpa.service;

import java.util.List;

/**
 *
 * @author ahza0
 */
// Ini adalah abstrak method, karena didalam interface
public interface RepoService <T>{
    public T create(T obj);
    public T update(T obj);
    public T delete(Object id);
    public T getById(Object id);
    public List<T> getAll(); 
}
