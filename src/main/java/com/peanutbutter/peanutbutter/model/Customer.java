package com.peanutbutter.peanutbutter.model;

import com.peanutbutter.peanutbutter.base.Auditable;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;
    
    private String firstName;

    private String lastName;
    
    private String phoneNumber;

   

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String fname){
        this.firstName = fname;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }


    public int getCustomerID(){
        return customerID;
    }

    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }

    public String getCustomerName(){
        return firstName + " " + lastName;
    }

    

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneno){
        this.phoneNumber = phoneno;
    }
}
