package com.peanutbutter.peanutbutter.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peanutbutter.peanutbutter.dtos.AccountBalanceDto;
import com.peanutbutter.peanutbutter.dtos.BalanceSheetDto;
import com.peanutbutter.peanutbutter.dtos.IncomeStatementDto;
import com.peanutbutter.peanutbutter.dtos.LedgerEntryDto;
import com.peanutbutter.peanutbutter.service.FinancialReportService;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class FinancialReportController {

    private final FinancialReportService reportService;

    public FinancialReportController(FinancialReportService reportService){
        this.reportService = reportService;
    }

    @GetMapping("/trial-balance")
    public ResponseEntity<List<AccountBalanceDto>> trialBalance(@RequestParam(required = false) String from, @RequestParam(required = false) String to){
        LocalDate f = from == null ? null : LocalDate.parse(from);
        LocalDate t = to == null ? null : LocalDate.parse(to);
        return ResponseEntity.ok(reportService.trialBalance(f,t));
    }

    @GetMapping("/ledger/{accountId}")
    public ResponseEntity<List<LedgerEntryDto>> ledger(@PathVariable Long accountId, @RequestParam(required = false) String from, @RequestParam(required = false) String to){
        LocalDate f = from == null ? null : LocalDate.parse(from);
        LocalDate t = to == null ? null : LocalDate.parse(to);
        return ResponseEntity.ok(reportService.ledger(accountId,f,t));
    }

    @GetMapping("/income-statement")
    public ResponseEntity<IncomeStatementDto> incomeStatement(@RequestParam(required = false) String from, @RequestParam(required = false) String to){
        LocalDate f = from == null ? null : LocalDate.parse(from);
        LocalDate t = to == null ? null : LocalDate.parse(to);
        return ResponseEntity.ok(reportService.incomeStatement(f,t));
    }

    @GetMapping("/balance-sheet")
    public ResponseEntity<BalanceSheetDto> balanceSheet(@RequestParam(required = false) String asOf){
        LocalDate d = asOf == null ? LocalDate.now() : LocalDate.parse(asOf);
        return ResponseEntity.ok(reportService.balanceSheet(d));
    }

}
