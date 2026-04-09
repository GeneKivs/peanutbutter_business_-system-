package com.peanutbutter.peanutbutter.dtos;

import java.time.LocalDate;
import java.util.List;

public class SalesResponseDto {

    private Long salesID;
    private Long customerID;
    private String customerName;
    private List<SalesProductResponseDto> products;
    private List<PaymentResponseDto> payment;
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

    public List<SalesProductResponseDto> getProducts(){
        return products;
    }

    public void setProducts(List<SalesProductResponseDto> products){
        this.products = products;
    }

    public List<PaymentResponseDto> getPayment(){
        return payment;
    }

    public void setPayment(List<PaymentResponseDto> payment){
        this.payment = payment;
    }

    public void setUpdatedAt(LocalDate date){
        this.updatedAt = date;
    }

}
