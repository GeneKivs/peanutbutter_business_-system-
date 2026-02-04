package com.peanutbutter.peanutbutter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.model.Expense;
import com.peanutbutter.peanutbutter.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public void defineExpense(Expense expense){
        expenseRepository.save(expense);
    }
    
    public Expense getExpenseByID(int expenseID){
        return expenseRepository.findById(expenseID).orElse(null);
    }
    public List<Expense> getALlExpenses(){
        return expenseRepository.findAll();
    }

    public void updateExpenses(Expense expense){
        expenseRepository.save(expense);
    }

    public void deleteExpenseByID(int expenseID){
        expenseRepository.deleteById(expenseID);
    }

}
