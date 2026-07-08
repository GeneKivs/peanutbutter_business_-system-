package com.peanutbutter.peanutbutter.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.dtos.AccountBalanceDto;
import com.peanutbutter.peanutbutter.dtos.BalanceSheetDto;
import com.peanutbutter.peanutbutter.dtos.IncomeStatementDto;
import com.peanutbutter.peanutbutter.dtos.LedgerEntryDto;
import com.peanutbutter.peanutbutter.model.Account;
import com.peanutbutter.peanutbutter.model.JournalLine;
//import com.peanutbutter.peanutbutter.repository.AccountRepository;
import com.peanutbutter.peanutbutter.repository.JournalLineRepository;
//import com.peanutbutter.peanutbutter.repository.JournalEntryRepository;
import com.peanutbutter.peanutbutter.service.FinancialReportService;

@Service
public class FinancialReportServiceImpl implements FinancialReportService {

    private final JournalLineRepository journalLineRepository;
   

    public FinancialReportServiceImpl(JournalLineRepository journalLineRepository){
        this.journalLineRepository = journalLineRepository;
       
    }

    @Override
    public List<AccountBalanceDto> trialBalance(LocalDate from, LocalDate to){
        Map<Long, AccountBalanceDto> map = new HashMap<>();

        List<JournalLine> lines = journalLineRepository.findAll();

        for (JournalLine l : lines){
            if (l.getJournalEntry() == null || l.getJournalEntry().getEntryDate() == null) continue;
            LocalDate d = l.getJournalEntry().getEntryDate();
            if ((from != null && d.isBefore(from)) || (to != null && d.isAfter(to))) continue;

            Account a = l.getAccount();
            if (a == null) continue;
            AccountBalanceDto dto = map.computeIfAbsent(a.getAccountID(), id -> new AccountBalanceDto(id, a.getAccountName(), a.getAccountType() == null ? null : a.getAccountType().name(), BigDecimal.ZERO, BigDecimal.ZERO));

            if (l.getDebit() != null) dto.setDebit(dto.getDebit().add(l.getDebit()));
            if (l.getCredit() != null) dto.setCredit(dto.getCredit().add(l.getCredit()));
        }

        return new ArrayList<>(map.values()).stream()
            .sorted((x,y)-> x.getAccountName().compareToIgnoreCase(y.getAccountName()))
            .collect(Collectors.toList());
    }

    @Override
    public List<LedgerEntryDto> ledger(Long accountId, LocalDate from, LocalDate to){
        List<LedgerEntryDto> result = new ArrayList<>();
        List<JournalLine> lines = journalLineRepository.findAll();

        List<JournalLine> filtered = lines.stream()
            .filter(l -> l.getAccount() != null && l.getAccount().getAccountID() != null && l.getAccount().getAccountID().equals(accountId))
            .filter(l -> l.getJournalEntry() != null && l.getJournalEntry().getEntryDate() != null)
            .filter(l -> (from == null || !l.getJournalEntry().getEntryDate().isBefore(from)) && (to == null || !l.getJournalEntry().getEntryDate().isAfter(to)))
            .sorted((a,b) -> a.getJournalEntry().getEntryDate().compareTo(b.getJournalEntry().getEntryDate()))
            .collect(Collectors.toList());

        BigDecimal running = BigDecimal.ZERO;
        for (JournalLine l : filtered){
            LedgerEntryDto dto = new LedgerEntryDto();
            dto.setDate(l.getJournalEntry().getEntryDate());
            dto.setDescription(l.getJournalEntry().getDescription());
            dto.setDebit(l.getDebit() == null ? BigDecimal.ZERO : l.getDebit());
            dto.setCredit(l.getCredit() == null ? BigDecimal.ZERO : l.getCredit());
            running = running.add(dto.getDebit().subtract(dto.getCredit()));
            dto.setRunningBalance(running);
            dto.setJournalEntryId(l.getJournalEntry().getID());
            result.add(dto);
        }

        return result;
    }

    @Override
    public IncomeStatementDto incomeStatement(LocalDate from, LocalDate to){
        IncomeStatementDto dto = new IncomeStatementDto();
        List<AccountBalanceDto> tb = trialBalance(from,to);

        BigDecimal revenue = BigDecimal.ZERO;
        BigDecimal expenses = BigDecimal.ZERO;

        for (AccountBalanceDto a : tb){
            if ("REVENUE".equalsIgnoreCase(a.getAccountType())){
                // revenue typically credit balance
                revenue = revenue.add(a.getCredit().subtract(a.getDebit()));
            } else if ("EXPENSE".equalsIgnoreCase(a.getAccountType())){
                // expense typically debit balance
                expenses = expenses.add(a.getDebit().subtract(a.getCredit()));
            }
        }

        dto.setTotalRevenue(revenue);
        dto.setTotalExpenses(expenses);
        dto.setNetIncome(revenue.subtract(expenses));

        return dto;
    }

    @Override
    public BalanceSheetDto balanceSheet(LocalDate asOf){
        BalanceSheetDto dto = new BalanceSheetDto();
        List<AccountBalanceDto> tb = trialBalance(null, asOf);

        List<AccountBalanceDto> assets = new ArrayList<>();
        List<AccountBalanceDto> liabilities = new ArrayList<>();
        List<AccountBalanceDto> equity = new ArrayList<>();

        BigDecimal totalAssets = BigDecimal.ZERO;
        BigDecimal totalLiabilities = BigDecimal.ZERO;
        BigDecimal totalEquity = BigDecimal.ZERO;

        for (AccountBalanceDto a : tb){
            if (a.getAccountType() == null) continue;
            switch (a.getAccountType()){
                case "ASSET":
                    BigDecimal assetBalance = a.getDebit().subtract(a.getCredit());
                    a.setDebit(a.getDebit()); a.setCredit(a.getCredit());
                    assets.add(a);
                    totalAssets = totalAssets.add(assetBalance);
                    break;
                case "LIABILITY":
                    BigDecimal liabilityBalance = a.getCredit().subtract(a.getDebit());
                    liabilities.add(a);
                    totalLiabilities = totalLiabilities.add(liabilityBalance);
                    break;
                case "EQUITY":
                    BigDecimal equityBalance = a.getCredit().subtract(a.getDebit());
                    equity.add(a);
                    totalEquity = totalEquity.add(equityBalance);
                    break;
                default:
                    break;
            }
        }

        dto.setAssets(assets);
        dto.setLiabilities(liabilities);
        dto.setEquity(equity);
        dto.setTotalAssets(totalAssets);
        dto.setTotalLiabilities(totalLiabilities);
        dto.setTotalEquity(totalEquity);

        return dto;
    }

}
