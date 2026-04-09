package com.peanutbutter.peanutbutter.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentRequestDto {

    private LocalDate paymentDate;
    private Long salesID;
    private Long paymentMethodID;
    private BigDecimal amount;

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

}
