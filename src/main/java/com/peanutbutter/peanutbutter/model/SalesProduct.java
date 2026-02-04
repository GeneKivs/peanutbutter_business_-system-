package com.peanutbutter.peanutbutter.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "sale_product")
public class SalesProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private long salesProductID;

    @ManyToOne
    @JoinColumn(name = "salesID")
    private Sales sales;

    @ManyToOne
    @JoinColumn(name =  "batchID")
    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    private int quantity;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    public long getSalesProductID() {
        return salesProductID;
    }

    public void setSalesProductID(long salesProductID) {
        this.salesProductID = salesProductID;
    }

    public Sales getSales() {
        return sales;
    }

    public void setSales(Sales sales) {
        this.sales = sales;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
