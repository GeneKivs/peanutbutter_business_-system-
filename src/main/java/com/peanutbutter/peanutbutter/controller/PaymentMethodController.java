package com.peanutbutter.peanutbutter.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peanutbutter.peanutbutter.dtos.PaymentMethodRequestDto;
import com.peanutbutter.peanutbutter.dtos.PaymentMethodResponseDto;
import com.peanutbutter.peanutbutter.service.PaymentMethodService;

@RestController
@RequestMapping("/api/paymentMethods")
@CrossOrigin(origins = "*")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService){
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping
    public ResponseEntity<PaymentMethodResponseDto> createPaymentMethod(@RequestBody PaymentMethodRequestDto requestDto){
        PaymentMethodResponseDto responseDto = paymentMethodService.createPaymentMethod(requestDto);

        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }

    @GetMapping("/{paymentMethodID}")
    public ResponseEntity<PaymentMethodResponseDto> getPaymentMethod(@PathVariable Long paymentMethodID){
        PaymentMethodResponseDto responseDto = paymentMethodService.getPaymentMethodByID(paymentMethodID);

        return responseDto != null ? ResponseEntity.ok(responseDto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethodResponseDto>> getPaymenMethods(){
        List<PaymentMethodResponseDto> paymentMethods = paymentMethodService.getAllPaymentMethods();

        return ResponseEntity.ok(paymentMethods);
    }

    @PatchMapping("/{paymentMethodID}")
    public ResponseEntity<PaymentMethodResponseDto> updatePaymentMethod(@PathVariable Long paymentMethodID,@RequestBody PaymentMethodRequestDto requestDto){
        PaymentMethodResponseDto responseDto = paymentMethodService.updatePaymentMethod(paymentMethodID, requestDto);

        return ResponseEntity.ok(responseDto);
    }

}
