package com.peanutbutter.peanutbutter.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BatchProductResponseDto {

    private Long batchProductID;
    private Long batchid;
    private int productid;
    private String productName;
    private int productQuantity;
    private int productRemQuantity;
    private BigDecimal costPerTin;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Long getBatchProductID(){
        return batchProductID;
    }

    public void setBatchProductID(Long id){
        this.batchProductID = id;
    }

    public Long getBatchID(){
        return batchid;
    }

    public void setBatchID(Long id){
        this.batchid = id;
    }

    public int getProductID(){
        return productid;
    }

    public void setProductID(int id){
        this.productid = id;
    }

    public String getProductName(){
        return productName;
    }

    public void setProductName(String name){
        this.productName = name;
    }

    public int getProductQuantity(){
        return productQuantity;
    }

    public void setProductQuantity(int quantity){
        this.productQuantity = quantity;
    }

    public int getProductRemQuantity(){
        return productRemQuantity;
    }

    public void setProductRemQuantity(int quantity){
        this.productRemQuantity = quantity;
    }

    public BigDecimal getCostPerTin(){
        return costPerTin;
    }

    public void setCostPerTin(BigDecimal cost){
        this.costPerTin = cost;
    }

    public LocalDate getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDate date){
        this.createdAt = date;
    }

    public LocalDate getUpdatedAt(){
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate date){
        this.updatedAt = date;
    }





}
