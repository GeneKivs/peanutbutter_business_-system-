package com.peanutbutter.peanutbutter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peanutbutter.peanutbutter.model.Sales;
import com.peanutbutter.peanutbutter.model.SalesProduct;
import java.util.List;


public interface SalesProductRepository extends JpaRepository<SalesProduct,Long>{

    List<SalesProduct> findBySales(Sales sales);
}
