package com.peanutbutter.peanutbutter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.peanutbutter.peanutbutter.model.Sales;

public interface SalesRepository extends JpaRepository<Sales,Long> {

    

    @Query("SELECT s FROM Sales s LEFT JOIN FETCH s.salesProducts")
    List<Sales> findAllWithProducts();
}
