package com.peanutbutter.peanutbutter.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductResponseDto {

    private Long productID;
    private String productName;
    private int reorderLevel;
    private BigDecimal pricePerTin;
    private int availableStock;
    private Double size;
    private LocalDate createdAT;
    private LocalDate updatedAT;

    public Long getProductID(){
        return productID;
    }

    public void setProductID(Long id){
        this.productID = id;
    }

    public String productName(){
        return productName;
    }

    public void setProductName(String name){
        this.productName = name;
    }

    public int getReorderLevel(){
        return reorderLevel;
    }

    public void setReorderLevel(int level){
        this.reorderLevel = level;
    }

    public BigDecimal getPriceperTin(){
        return pricePerTin;
    }

    public void setPricePerTin(BigDecimal price){
        this.pricePerTin = price;
    }

    public int getAvailableStock(){
        return availableStock;
    }

    public void setAvailableStock(int stock){
        this.availableStock = stock;
    }

    public LocalDate getCreatedAT(){
        return createdAT;
    }

    public void setCreatedAt(LocalDate date){
        this.createdAT =date;
    }

    public LocalDate getUpdatedAt(){
        return updatedAT;
    }

    public void setUpdatedAt(LocalDate date){
        this.updatedAT = date;
    }

    public Double getSize(){
        return size;
    }

    public void setSize(Double size){
        this.size = size;
    }

}
