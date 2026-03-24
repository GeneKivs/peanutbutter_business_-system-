package com.peanutbutter.peanutbutter.mapper;

import com.peanutbutter.peanutbutter.dtos.PaymentResponseDto;
import com.peanutbutter.peanutbutter.model.Payment;

public class PaymentMapper {

    private PaymentMapper (){}

    public static PaymentResponseDto toResponseDto(Payment payment){
        PaymentResponseDto dto = new PaymentResponseDto();

        dto.setPaymentID(payment.getPaymentID());
        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        if (payment.getSales() != null) {
            dto.setSalesID(payment.getSales().getSalesid());
        }
        if (payment.getPaymentMethod() != null) {
            dto.setPaymentMethodID(payment.getPaymentMethod().getId());
            dto.setPaymentType(payment.getPaymentMethod().getPaymentType());
        }

        dto.setCreatedAt(payment.getCreatedAt());
        dto.setUpdatedAt(payment.getUpdatedAt());

        return dto;
    }

}
