package com.peanutbutter.peanutbutter.mapper;

import com.peanutbutter.peanutbutter.dtos.ExpenditureResponseDto;
import com.peanutbutter.peanutbutter.model.Expenditure;

public class ExpenditureMapper {

    private ExpenditureMapper(){}

    public static ExpenditureResponseDto toResponseDto(Expenditure expenditure){
        ExpenditureResponseDto dto = new ExpenditureResponseDto();

        dto.setExpenditureID(expenditure.getExpenditureID());
        dto.setAmountSpent(expenditure.getAmountSpent());
        dto.setExpenditureDate(expenditure.getExpenditureDate());
        if (expenditure.getExpense() != null) {
            dto.setExpenseID(expenditure.getExpense().getExpenseID());
            dto.setExpenseName(expenditure.getExpense().getExpenseName());
        }

        if (expenditure.getPaymentMethod() != null) {
            dto.setPaymentMethod(expenditure.getPaymentMethod().getId());
            dto.setPaymentType(expenditure.getPaymentMethod().getPaymentType());
        }

        dto.setCreatedAT(expenditure.getCreatedAt());
        dto.setUpdateAt(expenditure.getUpdatedAt());

        return dto;
    }

}
