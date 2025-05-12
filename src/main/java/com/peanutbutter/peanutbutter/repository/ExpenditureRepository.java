package com.peanutbutter.peanutbutter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.peanutbutter.peanutbutter.model.Expenditure;

public interface ExpenditureRepository extends JpaRepository<Expenditure,Long> {

    @Query(value = "SELECT * FROM expenditure where batchid = :batchID", nativeQuery = true)
    List<Expenditure> findByBatchID(@Param("batchID")int batchID);

}
