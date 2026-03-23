package com.peanutbutter.peanutbutter.mapper;

import com.peanutbutter.peanutbutter.dtos.BatchRequestDto;
import com.peanutbutter.peanutbutter.dtos.BatchResponseDto;
import com.peanutbutter.peanutbutter.model.Batch;

public class BatchMapper {

    private BatchMapper(){}

    public static Batch toEntity(BatchRequestDto dto){
        if (dto == null) {
            return null;
        }

        Batch batch = new Batch();
        batch.setReceivedDate(dto.getReceivedDate());
        batch.setPeanutQuantity(dto.getPeanutQuantity());
        batch.setAmountPaid(dto.getAmountPaid());

        return batch;

    }

public static BatchResponseDto toResponse(Batch batch){
    if (batch == null) {
        return null;
    }

    BatchResponseDto dto = new BatchResponseDto();
    //batch id is not yet set need to update itto long
    dto.setReceivedDate(batch.getReceivedDate());
    dto.setAmountPaid(batch.getAmountPaid());
    dto.setPeanutQuantity(batch.getPeanutQuantity());
    dto.setCreatedAt(batch.getCreatedAt());
    dto.setUpdatedAt(batch.getUpdatedAt());

    return dto;
}

}
