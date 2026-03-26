package com.peanutbutter.peanutbutter.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.dtos.ExpenseRequestDto;
import com.peanutbutter.peanutbutter.dtos.ExpenseResponseDto;
import com.peanutbutter.peanutbutter.mapper.ExpenseMapper;
import com.peanutbutter.peanutbutter.model.Expense;
import com.peanutbutter.peanutbutter.repository.ExpenseRepository;
import com.peanutbutter.peanutbutter.service.ExpenseServiceApi;

@Service
public class ExpenseServiceImpl implements ExpenseServiceApi {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl (ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    @Override
    public ExpenseResponseDto createExpense(ExpenseRequestDto requestDto){
        Expense expense = ExpenseMapper.toEntity(requestDto);
        Expense savedExpense = expenseRepository.save(expense);

        return ExpenseMapper.toResponse(savedExpense);
    }

    @Override
    public ExpenseResponseDto getExpenseByID(Long expenseID){
        return expenseRepository.findById(expenseID)
                .map(ExpenseMapper::toResponse)
                .orElseThrow();
    }

    @Override
    public ExpenseResponseDto updateExpense(Long expenseID,ExpenseRequestDto requestDto){
        Expense existingExpense = expenseRepository.findById(expenseID).orElseThrow();

        if (requestDto.getExpenseName() != null) {
            existingExpense.setExpenseName(requestDto.getExpenseName());
        }
        if (requestDto.getDescription() != null) {
            existingExpense.setDescription(requestDto.getDescription());
        }

        Expense updatedExpense = expenseRepository.save(existingExpense);

        return ExpenseMapper.toResponse(updatedExpense);
    }

    @Override
    public List<ExpenseResponseDto> getAllExpenses(){
        return expenseRepository.findAll()
                .stream()
                .map(ExpenseMapper::toResponse)
                .collect(Collectors.toList());
    }



}
