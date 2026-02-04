package com.peanutbutter.peanutbutter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.peanutbutter.peanutbutter.model.Expense;
import com.peanutbutter.peanutbutter.service.ExpenseService;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expensedef")
    public String showexpensedefform(Model model){
        Expense expense = new Expense();
        model.addAttribute("expense", expense);
        return "expensedef";

    }

    @PostMapping("/saveExpense")
    public String saveExpense(@ModelAttribute("expense") Expense expense,RedirectAttributes redirectAttributes){
        expenseService.defineExpense(expense);
        return "redirect:/expenses";
    }

    @GetMapping("/expenses")
    public String showExpensesList(Model model){
        List <Expense> expenses = expenseService.getALlExpenses();
        model.addAttribute("expenses", expenses);
        return "expenselist";
    }

    @GetMapping("/expenseEdit/{expenseID}")
    public String showExpenseEDitForm(@PathVariable("expenseID")int expenseID, Model model){
        Expense expense = expenseService.getExpenseByID(expenseID);
        model.addAttribute("expense", expense);
        return "expenseEdit";
    }

    @PostMapping("/updateExpense")
    public String updateExpense(@ModelAttribute("expense")Expense expense,RedirectAttributes redirectAttributes){
        expenseService.updateExpenses(expense);
        return "redirect:/expenses";
    }

}
