package com.peanutbutter.peanutbutter.model;

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

    @ManyToOne
    @JoinColumn(name = "batchID")
    private Batch batch;
    private LocalDate expenditureDate;
    private double amountSpent;

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

    public Batch getBatch(){
        return batch;
    }

    public void setBatch(Batch batch){
        this.batch = batch;
    }
    public LocalDate getExpenditureDate(){
        return expenditureDate;
    }

    public void setExpenditureDate(LocalDate date){
        this.expenditureDate = date;
    }

    public double getAmountSpent(){
        return amountSpent;
    }

    public void setAmountSpent(double amountSpent){
        this.amountSpent = amountSpent;
    }

}
