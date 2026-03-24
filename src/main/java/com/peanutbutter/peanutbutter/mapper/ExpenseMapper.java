package com.peanutbutter.peanutbutter.mapper;

import com.peanutbutter.peanutbutter.dtos.ExpenseRequestDto;
import com.peanutbutter.peanutbutter.dtos.ExpenseResponseDto;
import com.peanutbutter.peanutbutter.model.Expense;

public class ExpenseMapper {

    private ExpenseMapper (){}

    public static Expense toEntity(ExpenseRequestDto dto){
        if (dto == null) {
            return null;
        }

        Expense expense = new Expense();
        expense.setExpenseName(dto.getExpenseName());
        expense.setDescription(dto.getDescription());

        return expense;



    }


    public static ExpenseResponseDto toResponse(Expense expense){
        if (expense == null) {
            return null;
        }

        ExpenseResponseDto dto = new ExpenseResponseDto();
        dto.setExpenseID(expense.getExpenseID());
        dto.setEspenseName(expense.getExpenseName());
        dto.setDescrption(expense.getDescription());
        dto.setCreatedAT(expense.getCreatedAt());
        dto.setUpdatedAt(expense.getUpdatedAt());

        return dto;
    }

}
