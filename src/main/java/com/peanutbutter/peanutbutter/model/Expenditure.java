package com.peanutbutter.peanutbutter.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.peanutbutter.peanutbutter.base.Auditable;

import jakarta.persistence.*;

@Entity
@Table(name = "expenditure")
public class Expenditure extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenditureID;

    @ManyToOne
    @JoinColumn(name = "expenseID")
    private Expense expense;

    
    private LocalDate expenditureDate;

    @Column(precision = 10, scale = 2)
    private BigDecimal amountSpent;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    public long getExpenditureID(){
        return expenditureID;
    }

    public void setExpenditureID(long expenditureID){
        this.expenditureID = expenditureID;
    }

    public Expense getExpense (){
        return expense;
    }

    public void setExpense(Expense expense){
        this.expense = expense;
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

    public PaymentMethod getPaymentMethod(){
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod){
        this.paymentMethod = paymentMethod;
    }

}
