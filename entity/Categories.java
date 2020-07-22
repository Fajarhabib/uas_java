/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.northwind.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
@Table(name = "categories")
public class Categories {
    private static final long serialVersionUID = 1L;
     // untuk column disarankan huruf kecil semua  
    @Id
    // Untuk ID yang auto increment ada settingan dibawah, untuk insert automatis id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryid")
    @Size(max = 11)
    private Integer categoryID;
    
    @Column(name = "categoryname")
    @Size(max = 15)
    private String categoryName;
    
   
    
    // Buat Constructornya
    public Categories() {
    }

    public Categories(Integer categoryID, String categoryName, String description, String picture) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }
    
    // Buatkan Setter and Getter

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    
}
