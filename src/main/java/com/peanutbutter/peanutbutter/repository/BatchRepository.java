package com.peanutbutter.peanutbutter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.peanutbutter.peanutbutter.model.Batch;
import com.peanutbutter.peanutbutter.model.enums.BatchStatus;

public interface BatchRepository extends JpaRepository<Batch,Integer>{

    @Query(value = """
            SELECT b .* FROM batch b
            JOIN batch_product bp ON bp.batchid = b.batchid
            WhERE bp.productid = :productID
            AND b.batch_status = 'AVAILABLE'
            AND b.remaining_quantity > 0
            ORDER BY b.received_date ASC
            """, nativeQuery = true)
    List<Batch > findAvailableBatchesByProductID(@Param("productID")int productID);

    List<Batch> findByBatchStatus(BatchStatus batchStatus);

}
