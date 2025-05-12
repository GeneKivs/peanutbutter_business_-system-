package com.peanutbutter.peanutbutter.model;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentID;
    private String paymentType;

    public int getPaymentID(){
        return paymentID;
    }
    public void setPaymentID(int paymentID){
        this.paymentID = paymentID;
    }

    public String getPaymentType(){
        return paymentType;
    }

    public void setPaymentType(String type){
        this.paymentType = type;
    }

}
