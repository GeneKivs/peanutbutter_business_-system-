package com.peanutbutter.peanutbutter.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.peanutbutter.peanutbutter.model.Expense;
import com.peanutbutter.peanutbutter.model.Expenditure;

import com.peanutbutter.peanutbutter.repository.ExpenditureRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ExpenditureService {

    


    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenditureRepository expenditureRepository;

    @Autowired
    private AccountService accountService;

    public void definexpenditure( List<Integer> expenseIDs,String expenditureDate, String paymentAccount, HttpServletRequest request) {
        if (expenseIDs != null) {
            
            LocalDate expDate;
            if (expenditureDate != null && !expenditureDate.isEmpty()) {
                expDate = LocalDate.parse(expenditureDate, DateTimeFormatter.ISO_DATE);
            } else {
                expDate = LocalDate.now();
            }
            for (int expenseID : expenseIDs) {
                String paramName = "amount_" + expenseID;
                String amountStr = request.getParameter(paramName);

                if (amountStr != null && !amountStr.isEmpty()) {
                    BigDecimal amountSpent = new BigDecimal(amountStr);

                    Expense expense = expenseService.getExpenseByID(expenseID);

                    Expenditure expenditure = new Expenditure();
                    
                    expenditure.setExpense(expense);
                    expenditure.setAmountSpent(amountSpent);
                    expenditure.setExpenditureDate(expDate);

                    expenditureRepository.save(expenditure);

                    // Update the balance of the corresponding expense account
                    accountService.addToExpenseAccountBalance(expense.getExpenseName(), amountSpent);

                    // Reduce the balance from the payment account
                accountService.reduceAccountBalance(paymentAccount, amountSpent);
                }
            }
        }
    }

    public List<Expenditure> getAllExpenditures() {
        return expenditureRepository.findAll();
    }

    public Expenditure getExpenditureByID(long expenditureID) {
        return expenditureRepository.findById(expenditureID).orElse(null);
    }

    public List<Expenditure> getExpendituresbybatchID(int batchID) {
        return expenditureRepository.findByBatchID(batchID);
    }

    public void updateExpenditure(Expenditure expenditure) {
        expenditureRepository.save(expenditure);
    }

}
