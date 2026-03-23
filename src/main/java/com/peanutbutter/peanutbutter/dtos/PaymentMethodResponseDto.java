package com.peanutbutter.peanutbutter.dtos;

import java.time.LocalDate;

public class PaymentMethodResponseDto {

    private int paymentID;
    private String paymentType;
    private LocalDate createdAT;
    private LocalDate updatedAt;

    public int getPaymentID(){
        return paymentID;
    }

    public void setPaymentID(int id){
        this.paymentID = id;
    }

    public String getPaymentType(){
        return paymentType;
    }

    public void setPaymentType(String type){
        this.paymentType = type;
    }

    public LocalDate getCreatedAt(){
        return createdAT;
    }

    public void setCreatedAt(LocalDate date){
        this.createdAT = date;
    }

    public LocalDate getUpdatedAt(){
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate date){
        this.updatedAt = date;
    }



}
