package com.peanutbutter.peanutbutter.dtos;

import java.time.LocalDate;
import java.util.List;



public class SalesRequestDto {

    private Long customerID;
    private LocalDate salesDate;
    private List<SalesProductRequestDto> saleProducts;
    private List<PaymentRequestDto> payments;
    

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

    

    public List<SalesProductRequestDto> getSaleProducts(){
        return saleProducts;
    }

    public List<PaymentRequestDto> getPayments(){
        return payments;
    }


}
