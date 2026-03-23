package com.peanutbutter.peanutbutter.dtos;

public class PaymentMethodRequestDto {

    private String paymentType;

    public String getPaymentType(){
        return paymentType;
    }

    public void setPaymentType(String type){
        this.paymentType = type;
    }

}
