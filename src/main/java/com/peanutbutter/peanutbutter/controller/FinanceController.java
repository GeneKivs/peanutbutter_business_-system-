package com.peanutbutter.peanutbutter.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.peanutbutter.peanutbutter.model.Account;
import com.peanutbutter.peanutbutter.model.Payment;
import com.peanutbutter.peanutbutter.repository.AccountRepository;
import com.peanutbutter.peanutbutter.service.AccountService;
import com.peanutbutter.peanutbutter.service.PaymentService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FinanceController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/financeDashboard")
    public String showFinanceDashboard(){
        return "financedashboard";
    }

    @GetMapping("/Debtors")
    public String showDebtorslist(Model model){
        List<Account> debtAccounts = accountRepository.findcustomerAccounts();
        model.addAttribute("debtAccounts", debtAccounts);
        return "debtorslist";
    }

    @GetMapping("/DebtPayment/{accountID}")
    public String showDebtPaymentform(@PathVariable("accountID")int accountID, Model model ){
        Optional<Account> account = accountService.findAccountByID(accountID);
        model.addAttribute("account",account);

        List<Payment> payments = paymentService.getAllPaymentTypes();
        model.addAttribute("payments", payments);
        return "debtpayment";

    }

    @PostMapping("/saveDebtPayment")
    public String performDebtPayment(HttpServletRequest request){
        String accountName = request.getParameter("accountName");
        BigDecimal amount = new BigDecimal(request.getParameter("balance"));
        int paymentID =  Integer.parseInt(request.getParameter("paymentID"));   
       Payment payment = paymentService.getPaymentByID(paymentID);
       String paymentName = payment.getPaymentType();

        System.out.println(paymentName + " "+ accountName);

        accountService.makeDebtPayment(accountName, paymentName, amount);
        return "redirect:/Debtors";
    }



}
