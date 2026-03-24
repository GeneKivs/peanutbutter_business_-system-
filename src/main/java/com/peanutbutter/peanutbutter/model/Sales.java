package com.peanutbutter.peanutbutter.model;


import java.time.LocalDate;
import java.util.List;

import com.peanutbutter.peanutbutter.base.Auditable;


import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class Sales extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long salesid;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;

    private LocalDate  salesOrderDate;

   

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

   

   

    

   
    public LocalDate getSalesOrderDate() {
        return salesOrderDate;
    }

    public void setSalesOrderDate(LocalDate salesOrderDate) {
        this.salesOrderDate = salesOrderDate;
    }

   


    public List<SalesProduct> getSalesProducts() {
        return salesProducts;
    }
}
