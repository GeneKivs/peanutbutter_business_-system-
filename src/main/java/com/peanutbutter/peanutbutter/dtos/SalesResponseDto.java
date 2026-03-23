package com.peanutbutter.peanutbutter.dtos;

import java.time.LocalDate;

public class SalesResponseDto {

    private Long salesID;
    private Long customerID;
    private String customerName;
    private LocalDate salesDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Long getSalesID(){
        return salesID;
    }

    public void setSalesID(Long id){
        this.salesID =id;
    }

    public Long getCustomerId(){
        return customerID;
    }

    public void setCustomerID(Long id){
        this.customerID = id;
    }

    public String getCustomerName(){
        return customerName;
    }

    public void setCustomerName(String name){
        this.customerName = name;
    }

    public LocalDate getSalesDate(){
        return salesDate;
    }

    public void setSalesDate(LocalDate date){
        this.salesDate = date;
    }

    public LocalDate getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAT(LocalDate date){
        this.createdAt = date;
    }

    public LocalDate getUpdatedAt(){
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate date){
        this.updatedAt = date;
    }

}
