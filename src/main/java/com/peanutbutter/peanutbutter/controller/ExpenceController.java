package com.peanutbutter.peanutbutter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.peanutbutter.peanutbutter.model.Expence;
import com.peanutbutter.peanutbutter.service.ExpenceService;

@Controller
public class ExpenceController {

    @Autowired
    private ExpenceService expenceService;

    @GetMapping("/expencedef")
    public String showexpencedefform(Model model){
        Expence expence = new Expence();
        model.addAttribute("expence", expence);
        return "expencedef";

    }

    @PostMapping("/saveExpence")
    public String saveExpence(@ModelAttribute("expence") Expence expence,RedirectAttributes redirectAttributes){
        expenceService.defineExpence(expence);
        return "redirect:/expences";
    }

    @GetMapping("/expences")
    public String showExpencesList(Model model){
        List <Expence> expences = expenceService.getALlExpences();
        model.addAttribute("expences", expences);
        return "expencelist";
    }

}
