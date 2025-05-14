package com.peanutbutter.peanutbutter.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;

    private String productName;

    private int quantityInStock;

    @Column(precision = 10, scale = 2)
    private BigDecimal pricePerTin;

    private int reorderLevel;

    @ManyToOne
    @JoinColumn(name = "unitID")
    private Unit unit;

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getProductID(){
        return productID;
    }

    public void setProductID(int productID){
        this.productID = productID;
    }

    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public int getQuantityInStock(){
        return quantityInStock;
    }

    public void setQuantityInStock(int stock){
        this.quantityInStock = stock;
    }

    public BigDecimal getPricePerTin(){
        return pricePerTin;
    }

    public void setPricePerTin(BigDecimal price){
        this.pricePerTin = price;
    }

    public int getReorderLevel(){
        return reorderLevel;
    }

    public void setReorderLevel(int reorderlevel){
        this.reorderLevel = reorderlevel;
    }

    public String getQuantity(){
        return quantityInStock + "" + unit;
    }

}
