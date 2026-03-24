package com.peanutbutter.peanutbutter.model;

import java.math.BigDecimal;

import com.peanutbutter.peanutbutter.base.Auditable;

import jakarta.persistence.*;

//links batch to a product

@Entity
@Table(name = "batch_product")
public class BatchProduct extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batch_productID;

    @ManyToOne
    @JoinColumn(name = "batchID")
    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;
    

    private int productQuantity;

    private int productRemQuantity;

    @Column(precision = 10, scale = 2)
    private BigDecimal costPerUnit;

    public BigDecimal getCostPerUnit() {
        return costPerUnit;
    }
    public void setCostPerUnit(BigDecimal cost) {
        this.costPerUnit = cost;
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
