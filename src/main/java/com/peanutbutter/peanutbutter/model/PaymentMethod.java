package com.peanutbutter.peanutbutter.model;

import com.peanutbutter.peanutbutter.base.Auditable;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_method")
public class PaymentMethod extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String paymentType;
    
    private String description;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getPaymentType(){
        return paymentType;
    }

    public void setPaymentType(String type){
        this.paymentType = type;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

}
