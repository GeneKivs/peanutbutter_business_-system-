package com.peanutbutter.peanutbutter.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

//links batch to a product

@Entity
@Table(name = "batch_product")
public class BatchProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long batch_productID;

    @ManyToOne
    @JoinColumn(name = "batchID")
    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    private int productQuantity;

    private int productRemQuantity;

    @Column(precision = 10, scale = 2)
    private BigDecimal productRevenue;

    public BigDecimal getProductRevenue() {
        return productRevenue;
    }
    public void setProductRevenue(BigDecimal productRevenue) {
        this.productRevenue = productRevenue;
    }
    public int getProductRemQuantity() {
        return productRemQuantity;
    }
    public void setProductRemQuantity(int productRemQuantity) {
        this.productRemQuantity = productRemQuantity;
    }
    
    public int getProductQuantity() {
        return productQuantity;
    }
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
    public long getBatch_productID() {
        return batch_productID;
    }
    public void setBatch_productID(long batch_productID) {
        this.batch_productID = batch_productID;
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

}
