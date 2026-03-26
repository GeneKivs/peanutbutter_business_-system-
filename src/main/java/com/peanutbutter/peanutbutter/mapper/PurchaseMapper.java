package com.peanutbutter.peanutbutter.mapper;

import com.peanutbutter.peanutbutter.dtos.PurchaseResponseDto;
import com.peanutbutter.peanutbutter.model.Purchase;

public class PurchaseMapper  {

    private PurchaseMapper(){}

    public static PurchaseResponseDto toResponse(Purchase purchase){
        PurchaseResponseDto dto = new PurchaseResponseDto();

        dto.setPurchaseID(purchase.getPurchaseID());
        dto.setPurchaseDate(purchase.getPurchaseDate());
        if (purchase.getPaymentMethod() != null) {
            dto.setPaymentMethodID(purchase.getPaymentMethod().getId());
            dto.setPaymentType(purchase.getPaymentMethod().getPaymentType());
            
        }
        dto.setAmountPaid(purchase.getAmounPaid());
        dto.setPeanutQuantity(purchase.getPeanutQuantity());
        dto.setCreatedAt(purchase.getCreatedAt());
        dto.setUpdatedAt(purchase.getUpdatedAt());

        return dto;
    }

}
