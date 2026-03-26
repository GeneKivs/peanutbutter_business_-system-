package com.peanutbutter.peanutbutter.service;

import java.util.List;

import com.peanutbutter.peanutbutter.dtos.PaymentMethodRequestDto;
import com.peanutbutter.peanutbutter.dtos.PaymentMethodResponseDto;

public interface PaymentMethodService {

    PaymentMethodResponseDto createPaymentMethod(PaymentMethodRequestDto requestDto);
    PaymentMethodResponseDto getPaymentMethodByID(Long paymentMethodID);
    PaymentMethodResponseDto updatePaymentMethod(Long PaymentMethodID,PaymentMethodRequestDto requestDto);
    List<PaymentMethodResponseDto> getAllPaymentMethods();

}
