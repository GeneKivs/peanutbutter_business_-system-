package com.peanutbutter.peanutbutter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.peanutbutter.peanutbutter.model.BatchProduct;

public interface BatchProductRepository extends JpaRepository<BatchProduct,Long>{

    BatchProduct findByBatch_BatchIDAndProduct_ProductID(Long batchID, long productID);

    @Query(
        value = """
            SELECT * FROM batch_product 
            WHERE productid = :productID AND product_rem_quantity > 0
            ORDER BY createdAT ASC""",nativeQuery = true)
    List<BatchProduct> findByProductIDAndProductRemQuantity(Long productID);

    @Query(value = "SELECT * FROM batch_product WHERE batchID = :batchID",nativeQuery = true)
    BatchProduct findByBatch_BatchID(Long batchID);

    @Query(value = "SELECT SUM(product_rem_quantity) FROM batch_product  WHERE batchID = :batchID ",nativeQuery = true)
    Integer sumRemainingQuantityByBatchID(@Param("batchID") int batchID);

}
