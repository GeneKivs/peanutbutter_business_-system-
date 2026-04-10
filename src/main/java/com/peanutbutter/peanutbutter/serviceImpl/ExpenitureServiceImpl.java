package com.peanutbutter.peanutbutter.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.dtos.ExpenditureRequestDto;
import com.peanutbutter.peanutbutter.dtos.ExpenditureResponseDto;
import com.peanutbutter.peanutbutter.mapper.ExpenditureMapper;
import com.peanutbutter.peanutbutter.model.Account;
import com.peanutbutter.peanutbutter.model.Expenditure;
import com.peanutbutter.peanutbutter.model.Expense;
import com.peanutbutter.peanutbutter.model.JournalEntry;
import com.peanutbutter.peanutbutter.model.JournalLine;
import com.peanutbutter.peanutbutter.model.PaymentMethod;
import com.peanutbutter.peanutbutter.repository.AccountRepository;
import com.peanutbutter.peanutbutter.repository.ExpenditureRepository;
import com.peanutbutter.peanutbutter.repository.ExpenseRepository;
import com.peanutbutter.peanutbutter.repository.JournalEntryRepository;
import com.peanutbutter.peanutbutter.repository.JournalLineRepository;
import com.peanutbutter.peanutbutter.repository.PaymentMethodRepository;
import com.peanutbutter.peanutbutter.service.ExpenditureServiceApi;

@Service
public class ExpenitureServiceImpl implements ExpenditureServiceApi{

    private final ExpenseRepository expenseRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final ExpenditureRepository expenditureRepository;
    private final JournalEntryRepository journalEntryRepository;
    private final JournalLineRepository journalLineRepository;
    private final AccountRepository accountRepository;

    public ExpenitureServiceImpl(ExpenseRepository expenseRepository,PaymentMethodRepository paymentMethodRepository,ExpenditureRepository expenditureRepository,JournalEntryRepository journalEntryRepository,JournalLineRepository journalLineRepository,AccountRepository accountRepository){
        this.expenseRepository = expenseRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.expenditureRepository = expenditureRepository;
        this.journalEntryRepository = journalEntryRepository;
        this.journalLineRepository = journalLineRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public ExpenditureResponseDto createExpenditure(ExpenditureRequestDto requestDto){
        Expense expense = expenseRepository.findById(requestDto.getExpenseID()).orElseThrow(() -> new RuntimeException("Expense not found"));
        PaymentMethod paymentMethod = paymentMethodRepository.findById(requestDto.getPaymentMethod()).orElseThrow(() -> new RuntimeException("PaymentMethod not found"));

        Expenditure expenditure = new Expenditure();
        expenditure.setExpenditureDate(requestDto.getExpenditureDate());
        expenditure.setExpense(expense);
        expenditure.setPaymentMethod(paymentMethod);
        expenditure.setAmountSpent(requestDto.getAmountSpent());

        Expenditure savedExpenditure = expenditureRepository.save(expenditure);
        expenditureAccounting(savedExpenditure);

        return ExpenditureMapper.toResponseDto(savedExpenditure);

    }

    @Override
    public ExpenditureResponseDto getExpenditureById(Long expenditureID){
        return expenditureRepository.findById(expenditureID)
                .map(ExpenditureMapper:: toResponseDto)
                .orElseThrow(() -> new RuntimeException("Expenditure not found"));

    }

    @Override
    public List<ExpenditureResponseDto> getAllExpenditures(){
        return expenditureRepository.findAll()
                .stream()
                .map(ExpenditureMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenditureResponseDto updateExpenditure(Long expenditureID, ExpenditureRequestDto requestDto){
        Expenditure existingExpenditure = expenditureRepository.findById(expenditureID).orElseThrow(() -> new RuntimeException("Expenditure not found"));

        if (requestDto.getExpenditureDate() != null) {
            existingExpenditure.setExpenditureDate(requestDto.getExpenditureDate());
        }

        if (requestDto.getExpenseID() != null) {
            Expense expense = expenseRepository.findById(requestDto.getExpenseID()).orElseThrow(() -> new RuntimeException("Expense not found"));
            existingExpenditure.setExpense(expense);
        }

        if (requestDto.getPaymentMethod() != null) {
            PaymentMethod paymentMethod = paymentMethodRepository.findById(requestDto.getPaymentMethod()).orElseThrow(() -> new RuntimeException("PaymentMethod not found"));
            existingExpenditure.setPaymentMethod(paymentMethod);
            
        }

        existingExpenditure.setAmountSpent(requestDto.getAmountSpent());

        return ExpenditureMapper.toResponseDto(expenditureRepository.save(existingExpenditure));
    }

    public void expenditureAccounting(Expenditure expenditure){

        JournalEntry entry = new JournalEntry();
        entry.setEntryDate(expenditure.getExpenditureDate());
        entry.setReferenceID(expenditure.getExpenditureID());

        JournalEntry savedEntry = journalEntryRepository.save(entry);

        Account expenseAcc = accountRepository.findByAccountName(expenditure.getExpense().getExpenseName());
        Account paymentACC = accountRepository.findByAccountName(expenditure.getPaymentMethod().getPaymentType());


        JournalLine debit = new JournalLine();
        debit.setJournalEntry(savedEntry);
        debit.setAccount(expenseAcc);
        debit.setDebit(expenditure.getAmountSpent());

        journalLineRepository.save(debit);

        JournalLine credit = new JournalLine();
        credit.setJournalEntry(savedEntry);
        credit.setAccount(paymentACC);
        credit.setCredit(expenditure.getAmountSpent());

        journalLineRepository.save(credit);

    }

}
