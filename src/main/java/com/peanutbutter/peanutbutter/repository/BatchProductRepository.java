package com.peanutbutter.peanutbutter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.peanutbutter.peanutbutter.model.BatchProduct;

public interface BatchProductRepository extends JpaRepository<BatchProduct,Long>{

    BatchProduct findByBatch_BatchIDAndProduct_ProductID(int batchID, int productID);

    @Query(value = "SELECT SUM(product_rem_quantity) FROM batch_product  WHERE batchID = :batchID ",nativeQuery = true)
    Integer sumRemainingQuantityByBatchID(@Param("batchID") int batchID);

}
