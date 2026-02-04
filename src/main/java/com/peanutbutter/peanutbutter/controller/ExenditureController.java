package com.peanutbutter.peanutbutter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.peanutbutter.peanutbutter.model.Account;
import com.peanutbutter.peanutbutter.model.Batch;
import com.peanutbutter.peanutbutter.model.Expense;
import com.peanutbutter.peanutbutter.model.Expenditure;
import com.peanutbutter.peanutbutter.repository.AccountRepository;
import com.peanutbutter.peanutbutter.service.BatchService;
import com.peanutbutter.peanutbutter.service.ExpenseService;
import com.peanutbutter.peanutbutter.service.ExpenditureService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ExenditureController {

    @Autowired
    private ExpenditureService expenditureService;

    @Autowired
    private BatchService batchService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expendituredef")
    public String showExpenditureForm(Model model){
        Expenditure expenditure = new Expenditure();
        model.addAttribute("expenditure", expenditure);

        List<Batch> batches = batchService.getAvailableBatches();
        
         model.addAttribute("batches", batches);

         List<Account> accounts = accountRepository.findAllBankaccounts();
         model.addAttribute("accounts", accounts);
            
        
       

        List<Expense> expenses = expenseService.getALlExpenses();
        model.addAttribute("expenses", expenses);
        return "expenditureform";
    }

    @PostMapping("/saveExpenditure")
    public String saveExpenditure(
            
            @RequestParam(value = "expenseIDs") List<Integer> expenseIDs,
            @RequestParam(value = "expenditureDate", required = false) String expenditureDate,
            @RequestParam("paymentAccount") String paymentAccount,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        expenditureService.definexpenditure(expenseIDs, expenditureDate, paymentAccount, request);;
        return "redirect:/expenditures";
    }

    @GetMapping("/expenditures")
    public String showExpenditureList(Model model){
        List<Expenditure> expenditures = expenditureService.getAllExpenditures();
        model.addAttribute("expenditures", expenditures);
       
        return "expenditurelist";
    }

    @GetMapping("/expenditureEdit/{expenditureID}")
    public String showExpenditurEditForm(@PathVariable("expenditureID")long expenditureID, Model model){
        Expenditure expenditure = expenditureService.getExpenditureByID(expenditureID);
        model.addAttribute("expenditure", expenditure);

        List<Batch> batches = batchService.getAvailableBatches();
        
         model.addAttribute("batches", batches);

         List<Account> accounts = accountRepository.findAllBankaccounts();
         model.addAttribute("accounts", accounts);
            
        
       

        List<Expense> expenses = expenseService.getALlExpenses();
        model.addAttribute("expenses", expenses);
        return "expenditureEdit";
    }

    public String updateExpenditure(@ModelAttribute("expenditure")Expenditure expenditure, RedirectAttributes redirectAttributes){
        expenditureService.updateExpenditure(expenditure);
        return "redirect:/expenditures";
    }

}
