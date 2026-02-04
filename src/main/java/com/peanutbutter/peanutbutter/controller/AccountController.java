package com.peanutbutter.peanutbutter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.peanutbutter.peanutbutter.model.Account;
import com.peanutbutter.peanutbutter.model.Customer;
import com.peanutbutter.peanutbutter.model.Expense;
import com.peanutbutter.peanutbutter.service.AccountService;
import com.peanutbutter.peanutbutter.service.CustomerService;
import com.peanutbutter.peanutbutter.service.ExpenseService;

import jakarta.servlet.http.HttpServletRequest;



@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/accountDef")
    public String showAccountDefineForm(Model model) {
        List<Customer> customers = customerService.getAllCuatomers();
        List<Expense> expenses = expenseService.getALlExpenses();
        model.addAttribute("customers", customers);
        model.addAttribute("expenses", expenses);
        return "accountdefine";
    }

    @PostMapping("/saveAccount")
    public String saveAccount(HttpServletRequest request,RedirectAttributes redirectAttributes){
        String accountName = request.getParameter("accountName");
        String accountType = request.getParameter("accountType");
        String[] accountCategories = request.getParameterValues("accountCategory");
        String accountCategory = accountCategories != null ? String.join(",", accountCategories) : "";

        // Get selected customer or expense name
    String customerName = request.getParameter("customerName");
    String expenseName = request.getParameter("expenseName");

    // Use selected name if relevant
    if ("CUSTOMER".equalsIgnoreCase(accountCategory) && customerName != null && !customerName.isEmpty()) {
        accountName = customerName;
    } else if ("EXPENSE".equalsIgnoreCase(accountCategory) && expenseName != null && !expenseName.isEmpty()) {
        accountName = expenseName;
    }
        accountService.defineAccount(accountName, accountType, accountCategory, request);
         redirectAttributes.addFlashAttribute("successMessage", "Account created succesful.");
        return "redirect:/accountDef";
    }

    @GetMapping("/accounts")
    public String showAccounts(Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);

        return "accounts";
    }

}
