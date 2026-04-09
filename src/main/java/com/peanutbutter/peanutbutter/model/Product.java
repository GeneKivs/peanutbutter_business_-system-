package com.peanutbutter.peanutbutter.model;

import java.math.BigDecimal;

import com.peanutbutter.peanutbutter.base.Auditable;


import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;

    private String productName;

    

    @Column(precision = 10, scale = 2)
    private BigDecimal pricePerTin;

    private int reorderLevel;

    private Double size;


    public Long getProductID(){
        return productID;
    }

    public void setProductID(Long productID){
        this.productID = productID;
    }

    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

   

    public BigDecimal getPricePerTin(){
        return pricePerTin;
    }

    public void setPricePerTin(BigDecimal price){
        this.pricePerTin = price;
    }

    public int getReorderLevel(){
        return reorderLevel;
    }

    public void setReorderLevel(int reorderlevel){
        this.reorderLevel = reorderlevel;
    }

    public Double getSize(){
        return size;
    }

    public void setSize(Double size){
        this.size = size;
    }

   

}
