package com.peanutbutter.peanutbutter.service;


import java.util.List;

import com.peanutbutter.peanutbutter.dtos.BatchRequestDto;
import com.peanutbutter.peanutbutter.dtos.BatchResponseDto;
import com.peanutbutter.peanutbutter.model.Batch;
import com.peanutbutter.peanutbutter.model.BatchProduct;
import com.peanutbutter.peanutbutter.model.Purchase;

public interface BatchServiceApi {

    BatchResponseDto createBatch(BatchRequestDto requestDto);
    BatchResponseDto getBatchByID(Long batchID);
    List<BatchResponseDto> getAllBatches();
    BatchResponseDto updateBatch(Long batchID,BatchRequestDto requestDto);
    void calculateCostPerUnit(Batch batch, Purchase purchase,List<BatchProduct> batchProducts);

}
