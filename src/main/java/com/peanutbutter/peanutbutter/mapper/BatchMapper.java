package com.peanutbutter.peanutbutter.mapper;


import com.peanutbutter.peanutbutter.dtos.BatchResponseDto;
import com.peanutbutter.peanutbutter.model.Batch;

public class BatchMapper {

    private BatchMapper(){}

    

public static BatchResponseDto toResponse(Batch batch){
    if (batch == null) {
        return null;
    }

    BatchResponseDto dto = new BatchResponseDto();
    //batch id is not yet set need to update itto long
    dto.setBatchID(batch.getBatchID());
    dto.setReceivedDate(batch.getReceivedDate());
    if (batch.getPurchase() != null) {
        dto.setPurchaseID(batch.getPurchase().getPurchaseID());
    }
   
    dto.setCreatedAt(batch.getCreatedAt());
    dto.setUpdatedAt(batch.getUpdatedAt());

    return dto;
}

}
