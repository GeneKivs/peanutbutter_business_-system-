package com.peanutbutter.peanutbutter.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.peanutbutter.peanutbutter.model.enums.SalesStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long salesid;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "paymentID")
    private Payment payment;

    @Column(precision = 10 , scale = 2)
    private BigDecimal totalAmount;//will be calculated amount by the system

    @Column(precision = 10 , scale = 2)
    private BigDecimal amountPaid;

   
     @Column(precision = 10 , scale = 2)
    private BigDecimal deliveryCost;

     @Column(precision = 10 , scale = 2)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private SalesStatus salesStatus;

    private LocalDate  salesOrderDate;

    private LocalDate expectedDeliveryDate;

    private LocalDate deliveryDate;

    @OneToMany(mappedBy = "sales", fetch = FetchType.LAZY)
    private List<SalesProduct> salesProducts;

    public long getSalesid() {
        return salesid;
    }

    public void setSalesid(long salesid) {
        this.salesid = salesid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(BigDecimal deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public SalesStatus getSalesStatus() {
        return salesStatus;
    }

    public void setSalesStatus(SalesStatus salesStatus) {
        this.salesStatus = salesStatus;
    }

    public LocalDate getSalesOrderDate() {
        return salesOrderDate;
    }

    public void setSalesOrderDate(LocalDate salesOrderDate) {
        this.salesOrderDate = salesOrderDate;
    }

    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<SalesProduct> getSalesProducts() {
        return salesProducts;
    }
}
