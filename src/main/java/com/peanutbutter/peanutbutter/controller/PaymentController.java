package com.peanutbutter.peanutbutter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.peanutbutter.peanutbutter.model.Payment;
import com.peanutbutter.peanutbutter.service.PaymentService;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("paymentdef")
    public String showPaymentDefForm(Model model){
        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        return "paymentdef";
    }

    @PostMapping("/savePayment")
    public String savePymentType(@ModelAttribute("payment") Payment payment, RedirectAttributes redirectAttributes){
        paymentService.definePayment(payment);
        return "redirect:/payments";

    }

    @GetMapping("/payments")
    public String showPaymentList(Model model){
        List <Payment> payments = paymentService.getAllPaymentTypes();
        model.addAttribute("payments", payments);
        return "paymentlist";
    }


}
