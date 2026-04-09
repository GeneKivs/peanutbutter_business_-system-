package com.peanutbutter.peanutbutter.service;

import java.util.List;

import com.peanutbutter.peanutbutter.dtos.PaymentRequestDto;
import com.peanutbutter.peanutbutter.dtos.PaymentResponseDto;

public interface PaymentServiceApi {

    PaymentResponseDto makePayment(PaymentRequestDto requestDto);

    PaymentResponseDto getPaymentByID(Long paymentID);

    List<PaymentResponseDto> getAllPayments();

    PaymentResponseDto patchPayment(Long paymentID,PaymentRequestDto requestDto);

}
