package com.peanutbutter.peanutbutter.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peanutbutter.peanutbutter.dtos.ExpenseRequestDto;
import com.peanutbutter.peanutbutter.dtos.ExpenseResponseDto;
import com.peanutbutter.peanutbutter.service.ExpenseServiceApi;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseControllerApi {

    private final ExpenseServiceApi expenseServiceApi;

    public ExpenseControllerApi (ExpenseServiceApi expenseServiceApi){
        this.expenseServiceApi = expenseServiceApi;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponseDto> createProduct(@RequestBody ExpenseRequestDto requestDto){
        ExpenseResponseDto responseDto = expenseServiceApi.createExpense(requestDto);

        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }  

    @GetMapping("/{expenseID}")
    public ResponseEntity<ExpenseResponseDto> getExpense(@PathVariable Long expenseID){
        ExpenseResponseDto responseDto = expenseServiceApi.getExpenseByID(expenseID);

        return responseDto != null? ResponseEntity.ok(responseDto) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{expenseID}")
    public ResponseEntity<ExpenseResponseDto> updateExpense(@PathVariable Long expenseID,@RequestBody ExpenseRequestDto requestDto){
        ExpenseResponseDto responseDto = expenseServiceApi.updateExpense(expenseID, requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponseDto>> getAllExpenses(){
        List<ExpenseResponseDto> responseDtos = expenseServiceApi.getAllExpenses();

        return ResponseEntity.ok(responseDtos);
    }

}
