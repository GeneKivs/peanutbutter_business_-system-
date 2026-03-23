package com.peanutbutter.peanutbutter.dtos;

import java.time.LocalDate;

public class SalesRequestDto {

    private Long customerID;
    private LocalDate salesDate;
    

    public Long getCustomerID(){
        return customerID;
    }

    public void setCustomerID(Long id){
        this.customerID = id;
    }

    public LocalDate getSalesDate(){
        return salesDate;
    }

    public void setSalesDate(LocalDate date){
        this.salesDate = date;
    }

}
