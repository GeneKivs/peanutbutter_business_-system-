package com.peanutbutter.peanutbutter.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.peanutbutter.peanutbutter.base.Auditable;

import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class Payment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentID;

    @ManyToOne
    @JoinColumn(name = "salesid")
    private Sales sales;

    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    private BigDecimal amount;
    
   

    public Long getPaymentID(){
        return paymentID;
    }

    public void setPaymentID(Long paymentID){
        this.paymentID = paymentID;
    }

    public Sales getSales(){
        return sales;
    }

    public void setSales(Sales sales){
        this.sales = sales;
    }

    public LocalDate getPaymentDate(){
        return paymentDate;
    }

    public void setPaymentDate(LocalDate date){
        this.paymentDate = date;
    }

    public PaymentMethod getPaymentMethod(){
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod method){
        this.paymentMethod = method;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }



  

}
