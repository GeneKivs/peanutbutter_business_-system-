package com.peanutbutter.peanutbutter.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentResponseDto {

    private Long paymentID;
    private LocalDate paymentDate;
    private Long salesID;
    private Long paymentMethodID;
    private String paymentType;
    private BigDecimal amount;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Long getPaymentID(){
        return paymentID;
    }

    public void setPaymentID(Long id){
        this.paymentID = id;
    }

    public Long getSalesID(){
        return salesID;
    }

    public void setSalesID(Long id){
        this.salesID = id;
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

    public BigDecimal getAmount(){
        return amount;

    }

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }

    public LocalDate getPaymentDate(){
        return paymentDate;
    }

    public void setPaymentDate(LocalDate date){
        this.paymentDate = date;
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
