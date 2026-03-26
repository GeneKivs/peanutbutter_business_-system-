package com.peanutbutter.peanutbutter.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PurchaseRequestDto {

    private LocalDate purchaseDate;
    private int peanutQuantity;
    private BigDecimal amountPaid;
    private Long paymentMethodID;

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

}
