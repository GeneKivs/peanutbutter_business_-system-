package com.peanutbutter.peanutbutter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peanutbutter.peanutbutter.dtos.BatchRequestDto;
import com.peanutbutter.peanutbutter.dtos.BatchResponseDto;
import com.peanutbutter.peanutbutter.service.BatchServiceApi;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {

    private final BatchServiceApi batchServiceApi;

    public InventoryController(BatchServiceApi batchServiceApi){
        this.batchServiceApi = batchServiceApi;
    }

    @PostMapping
    public ResponseEntity<BatchResponseDto> recieveBatch(@RequestBody BatchRequestDto requestDto){
        BatchResponseDto responseDto = batchServiceApi.createBatch(requestDto);

        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }

    @PatchMapping("/id")
    public ResponseEntity<BatchResponseDto> updateBatch(@PathVariable Long batchId,@RequestBody BatchRequestDto requestDto){
        BatchResponseDto responseDto = batchServiceApi.updateBatch(batchId, requestDto);

        return ResponseEntity.ok(responseDto);
    }

    

}
