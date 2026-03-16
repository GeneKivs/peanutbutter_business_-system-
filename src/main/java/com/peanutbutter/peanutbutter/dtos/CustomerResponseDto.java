package com.peanutbutter.peanutbutter.dtos;

import java.time.LocalDate;

public class CustomerResponseDto {

    private int customerId;
    private String firstName;
    private String lastName;
    private String customerName;
    private String phoneNumber;
    private LocalDate createdAT;
    private LocalDate updatedAT;


    public int getCustomerID(){
        return customerId;
    }

    public void setCustomerID(int  id){
        this.customerId = id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String name ){
        this.firstName = name;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String name){
        this.lastName = name;
    }

    public String getCustomerName(){
        return customerName;
    }

    public void setCustomerName(String name){
        this.customerName = name;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String number){
        this.phoneNumber = number;
    }

    public LocalDate getCreatedAT(){
        return createdAT;
    }

    public void setCreatedAT(LocalDate date){
        this.createdAT = date;
    }

    public LocalDate getUpdatedAt(){
        return updatedAT;
    }

    public void setUpdatedAt(LocalDate date){
        this.updatedAT = date;
    }

}
