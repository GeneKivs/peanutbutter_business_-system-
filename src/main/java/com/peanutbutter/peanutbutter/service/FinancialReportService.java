package com.peanutbutter.peanutbutter.service;

import java.time.LocalDate;
import java.util.List;

import com.peanutbutter.peanutbutter.dtos.AccountBalanceDto;
import com.peanutbutter.peanutbutter.dtos.BalanceSheetDto;
import com.peanutbutter.peanutbutter.dtos.IncomeStatementDto;
import com.peanutbutter.peanutbutter.dtos.LedgerEntryDto;

public interface FinancialReportService {
    List<AccountBalanceDto> trialBalance(LocalDate from, LocalDate to);
    List<LedgerEntryDto> ledger(Long accountId, LocalDate from, LocalDate to);
    IncomeStatementDto incomeStatement(LocalDate from, LocalDate to);
    BalanceSheetDto balanceSheet(LocalDate asOf);
}
