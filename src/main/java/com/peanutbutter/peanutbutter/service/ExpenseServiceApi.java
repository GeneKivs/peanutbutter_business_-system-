package com.peanutbutter.peanutbutter.service;

import java.util.List;

import com.peanutbutter.peanutbutter.dtos.ExpenseRequestDto;
import com.peanutbutter.peanutbutter.dtos.ExpenseResponseDto;

public interface ExpenseServiceApi {

    ExpenseResponseDto createExpense(ExpenseRequestDto requestDto);
    ExpenseResponseDto getExpenseByID(Long expenseID);
    ExpenseResponseDto updateExpense(Long expenseID,ExpenseRequestDto requestDto);
    List<ExpenseResponseDto> getAllExpenses();

}
