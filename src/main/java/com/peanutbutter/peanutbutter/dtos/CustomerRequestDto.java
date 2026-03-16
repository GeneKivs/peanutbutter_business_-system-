package com.peanutbutter.peanutbutter.dtos;

public class CustomerRequestDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String name){
        this.firstName = name; 
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String name){
        this.lastName = name;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String number){
        this.phoneNumber = number;
    }

}
