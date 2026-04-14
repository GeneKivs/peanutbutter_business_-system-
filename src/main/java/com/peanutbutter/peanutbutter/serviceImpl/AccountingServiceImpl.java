package com.peanutbutter.peanutbutter.serviceImpl;

import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.model.Account;
import com.peanutbutter.peanutbutter.model.Expenditure;
import com.peanutbutter.peanutbutter.model.JournalEntry;
import com.peanutbutter.peanutbutter.model.JournalLine;
import com.peanutbutter.peanutbutter.model.Payment;
import com.peanutbutter.peanutbutter.model.Purchase;
import com.peanutbutter.peanutbutter.model.Sales;
import com.peanutbutter.peanutbutter.repository.AccountRepository;
import com.peanutbutter.peanutbutter.repository.JournalEntryRepository;
import com.peanutbutter.peanutbutter.repository.JournalLineRepository;
import com.peanutbutter.peanutbutter.service.AccountingService;

@Service
public class AccountingServiceImpl implements AccountingService {

    public final JournalEntryRepository journalEntryRepository;
    public final JournalLineRepository journalLineRepository;
    public final AccountRepository accountRepository;

    public AccountingServiceImpl (JournalEntryRepository journalEntryRepository,JournalLineRepository journalLineRepository,AccountRepository accountRepository){
        this.journalEntryRepository = journalEntryRepository;
        this.journalLineRepository = journalLineRepository;
        this.accountRepository = accountRepository;
    }

    public void paymentAccounting(Payment payment){
         
        JournalEntry entry = new JournalEntry();
        entry.setEntryDate(payment.getPaymentDate());
        entry.setReferenceID(payment.getPaymentID());

        JournalEntry savedEntry = journalEntryRepository.save(entry);

        Account paymentAcc = accountRepository.findByAccountName(payment.getPaymentMethod().getPaymentType());
        Account debtorAcc = accountRepository.findByAccountName("Debtors");

        JournalLine debit = new JournalLine();
        debit.setJournalEntry(savedEntry);
        debit.setAccount(paymentAcc);
        debit.setDebit(payment.getAmount());

        journalLineRepository.save(debit);

        JournalLine credit = new JournalLine();
        credit.setJournalEntry(savedEntry);
        credit.setAccount(debtorAcc);
        credit.setCredit(payment.getAmount());

        journalLineRepository.save(credit);
    }

    public void purchaseAccounting(Purchase purchase){

        //create the journal entry first
        JournalEntry entry = new JournalEntry();
        entry.setEntryDate(purchase.getPurchaseDate());
        entry.setReferenceID(purchase.getPurchaseID());

        JournalEntry savedEntry = journalEntryRepository.save(entry);

        //get the accounts to do double entry
        Account purchaseAcc = accountRepository.findByAccountName("Purchase");
        Account paymentAcc = accountRepository.findByAccountName(purchase.getPaymentMethod().getPaymentType());

        //create and save the debit side 
        JournalLine debit = new JournalLine();
        debit.setJournalEntry(savedEntry);
        debit.setAccount(purchaseAcc);
        debit.setDebit(purchase.getAmounPaid());
        
        journalLineRepository.save(debit);

        //create and save the credit side
        JournalLine credit = new JournalLine();
        credit.setJournalEntry(savedEntry);
        credit.setAccount(paymentAcc);
        credit.setCredit(purchase.getAmounPaid());

        journalLineRepository.save(credit);

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

    public void salesAccounting(Sales sales,Payment payment){

        JournalEntry entry = new JournalEntry();
        entry.setEntryDate(sales.getSalesOrderDate());
        entry.setReferenceID(sales.getSalesid());

        JournalEntry savedEntry = journalEntryRepository.save(entry);

        Account salesAcc = accountRepository.findByAccountName("Sales");
        Account paymentAcc = accountRepository.findByAccountName(payment.getPaymentMethod().getPaymentType());

        JournalLine debit = new JournalLine();
        debit.setJournalEntry(savedEntry);
        debit.setAccount(paymentAcc);
        debit.setDebit(payment.getAmount());

        journalLineRepository.save(debit);

        JournalLine credit = new JournalLine();
        credit.setJournalEntry(savedEntry);
        credit.setAccount(salesAcc);
        credit.setCredit(payment.getAmount());

        journalLineRepository.save(credit);
    }




}
