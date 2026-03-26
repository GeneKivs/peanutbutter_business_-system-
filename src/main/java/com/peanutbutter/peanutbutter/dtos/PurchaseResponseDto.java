package com.peanutbutter.peanutbutter.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PurchaseResponseDto {

    private Long purchaseID;
    private LocalDate purchaseDate;
    private int peanutQuantity;
    private BigDecimal amountPaid;
    private Long paymentMethodID;
    private String paymentType;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Long getPurchaseID(){
        return purchaseID;
    }

    public void setPurchaseID(Long id){
        this.purchaseID = id;
    }


     public LocalDate getPurchaseDate(){
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate date){
        this.purchaseDate = date;
    }

    public int getPeanutQuantity(){
        return peanutQuantity;
    }

    public void setPeanutQuantity(int quantity){
        this.peanutQuantity = quantity;
    }

    public BigDecimal getAmounPaid(){
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amount){
        this.amountPaid = amount;
    }

    public Long getPaymentMethodID(){
        return paymentMethodID;
    }

    public void setPaymentMethodID(Long id){
        this.paymentMethodID = id;
    }

    public String getPaymentType(){
        return paymentType;
    }

    public void setPaymentType(String type){
        this.paymentType = type;
    }

    public LocalDate getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDate date){
        this.createdAt = date;
    }

    public LocalDate getUpdatedAt(){
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate date){
        this.updatedAt = date;
    }

}
