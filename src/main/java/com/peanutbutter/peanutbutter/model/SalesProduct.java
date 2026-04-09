package com.peanutbutter.peanutbutter.model;

import java.math.BigDecimal;

import com.peanutbutter.peanutbutter.base.Auditable;

import jakarta.persistence.*;

@Entity
@Table(name = "sale_product")
public class SalesProduct extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private long salesProductID;

    @ManyToOne
    @JoinColumn(name = "salesID")
    private Sales sales;

  

    @ManyToOne
    @JoinColumn(name = "batch_productid")
    private BatchProduct batchProduct;

    private int quantity;

    @Column(precision = 10, scale = 2)
    private BigDecimal sellingPrice;

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

    

    public BatchProduct  getProducts() {
        return batchProduct;
    }

    public void setProducts(BatchProduct products) {
        this.batchProduct = products;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal price) {
        this.sellingPrice = price;
    }

}
