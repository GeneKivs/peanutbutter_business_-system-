package com.peanutbutter.peanutbutter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peanutbutter.peanutbutter.dtos.ExpenditureRequestDto;
import com.peanutbutter.peanutbutter.dtos.ExpenditureResponseDto;
import com.peanutbutter.peanutbutter.service.ExpenditureServiceApi;

@RestController
@RequestMapping("/api/expenditures")
@CrossOrigin(origins = "*")
public class ExpenditureController {

    private final ExpenditureServiceApi expenditureServiceApi;

    public ExpenditureController (ExpenditureServiceApi expenditureServiceApi){
        this.expenditureServiceApi = expenditureServiceApi;
    }

    @PostMapping
    public ResponseEntity<ExpenditureResponseDto> createExpenditure(@RequestBody ExpenditureRequestDto requestDto){
        ExpenditureResponseDto response = expenditureServiceApi.createExpenditure(requestDto);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}