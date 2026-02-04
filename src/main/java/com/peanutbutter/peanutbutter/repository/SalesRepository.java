package com.peanutbutter.peanutbutter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.peanutbutter.peanutbutter.model.Sales;
import com.peanutbutter.peanutbutter.model.enums.SalesStatus;


public interface SalesRepository extends JpaRepository<Sales,Long> {

    List<Sales> findBySalesStatus(SalesStatus salesStatus);

    @Query("SELECT s FROM Sales s LEFT JOIN FETCH s.salesProducts")
    List<Sales> findAllWithProducts();
}
