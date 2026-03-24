package com.peanutbutter.peanutbutter.mapper;

import com.peanutbutter.peanutbutter.dtos.PaymentMethodRequestDto;
import com.peanutbutter.peanutbutter.dtos.PaymentMethodResponseDto;
import com.peanutbutter.peanutbutter.model.PaymentMethod;

public class PaymentMethodMapper {

    private PaymentMethodMapper(){}
    
    public static PaymentMethod toEntity(PaymentMethodRequestDto dto){
        if (dto  == null) {
            return null;
        }

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentType(dto.getPaymentType());

        return paymentMethod;
    }

    public static PaymentMethodResponseDto toResponse(PaymentMethod paymentMethod){
        if (paymentMethod == null) {
            return null;
            
        }

        PaymentMethodResponseDto dto = new PaymentMethodResponseDto();
        dto.setPaymentType(paymentMethod.getPaymentType());
        dto.setPaymentMethodID(paymentMethod.getId());
        dto.setCreatedAt(paymentMethod.getCreatedAt());
        dto.setUpdatedAt(paymentMethod.getUpdatedAt());

        return dto;
    }

}
