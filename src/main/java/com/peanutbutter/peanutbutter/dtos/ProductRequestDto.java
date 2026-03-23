package com.peanutbutter.peanutbutter.dtos;

import java.math.BigDecimal;

public class ProductRequestDto {

    private String productName;
    private int reorderLevel;
    private BigDecimal pricePerTin;

    public String getProductName(){
        return productName;
    }

    public void setProduct(String name){
        this.productName = name;
    }

    public int getReorderLevel(){
        return reorderLevel;
    }

    public void setReorderLevel(int level){
        this.reorderLevel = level;
    }

    public BigDecimal getPricePerTin(){
        return pricePerTin;
    }

    public void setPricePerTin(BigDecimal price){
        this.pricePerTin = price;
    }

}
