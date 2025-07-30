package com.peanutbutter.peanutbutter.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "expenditure")
public class Expenditure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long expenditureID;

    @ManyToOne
    @JoinColumn(name = "expenceID")
    private Expence expence;

    
    private LocalDate expenditureDate;

    @Column(precision = 10, scale = 2)
    private BigDecimal amountSpent;

    public long getExpenditureID(){
        return expenditureID;
    }

    public void setExpenditureID(long expenditureID){
        this.expenditureID = expenditureID;
    }

    public Expence getExpence (){
        return expence;
    }

    public void setExpence(Expence expence){
        this.expence = expence;
    }

    
    public LocalDate getExpenditureDate(){
        return expenditureDate;
    }

    public void setExpenditureDate(LocalDate date){
        this.expenditureDate = date;
    }

    public BigDecimal getAmountSpent(){
        return amountSpent;
    }

    public void setAmountSpent(BigDecimal amountSpent){
        this.amountSpent = amountSpent;
    }

}
