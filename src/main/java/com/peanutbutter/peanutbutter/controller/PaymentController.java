package com.peanutbutter.peanutbutter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peanutbutter.peanutbutter.dtos.PaymentRequestDto;
import com.peanutbutter.peanutbutter.dtos.PaymentResponseDto;
import com.peanutbutter.peanutbutter.service.PaymentServiceApi;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    private final PaymentServiceApi paymentServiceApi;

    public PaymentController(PaymentServiceApi paymentServiceApi){
        this.paymentServiceApi = paymentServiceApi;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDto> makePayment(@RequestBody PaymentRequestDto requestDto){
        PaymentResponseDto responseDto = paymentServiceApi.makePayment(requestDto);

        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }

}
