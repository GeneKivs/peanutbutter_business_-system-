package com.peanutbutter.peanutbutter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peanutbutter.peanutbutter.dtos.SalesRequestDto;
import com.peanutbutter.peanutbutter.dtos.SalesResponseDto;
import com.peanutbutter.peanutbutter.service.SalesServiceApi;

@RestController
@RequestMapping("/api/sales")
public class SalesControllerApi {

    private final SalesServiceApi salesServiceApi;

    public SalesControllerApi (SalesServiceApi salesServiceApi){
        this.salesServiceApi = salesServiceApi;
    }

    @PostMapping
    public ResponseEntity<SalesResponseDto> createSales(@RequestBody SalesRequestDto requestDto){

        SalesResponseDto responseDto = salesServiceApi.processSales(requestDto);

        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);

    }

}
