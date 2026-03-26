package com.peanutbutter.peanutbutter.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.peanutbutter.peanutbutter.base.Auditable;

import jakarta.persistence.*;

@Entity
@Table(name = "purchase")
public class Purchase  extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseID;
    private LocalDate purchaseDate;
    private int peanutQuantity;

    @Column(precision = 10, scale = 2)
    private BigDecimal amountPaid;

    @ManyToOne
    @JoinColumn(name = "payment_method_id") 
    private PaymentMethod paymentMethod;

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

    public PaymentMethod getPaymentMethod(){
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod){
        this.paymentMethod = paymentMethod;
    }

}
