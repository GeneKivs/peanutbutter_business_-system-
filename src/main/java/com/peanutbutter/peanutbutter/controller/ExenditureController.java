package com.peanutbutter.peanutbutter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.peanutbutter.peanutbutter.model.Batch;
import com.peanutbutter.peanutbutter.model.Expence;
import com.peanutbutter.peanutbutter.model.Expenditure;

import com.peanutbutter.peanutbutter.service.BatchService;
import com.peanutbutter.peanutbutter.service.ExpenceService;
import com.peanutbutter.peanutbutter.service.ExpenditureService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ExenditureController {

    @Autowired
    private ExpenditureService expenditureService;

    @Autowired
    private BatchService batchService;

    @Autowired
    private ExpenceService expenceService;

    @GetMapping("/expendituredef")
    public String showExpenditureForm(Model model){
        Expenditure expenditure = new Expenditure();
        model.addAttribute("expenditure", expenditure);

        List<Batch> batches = batchService.getAvailableBatches();
        
         model.addAttribute("batches", batches);
            
        
       

        List<Expence> expences = expenceService.getALlExpences();
        model.addAttribute("expences", expences);
        return "expenditureform";
    }

    @PostMapping("/saveExpenditure")
    public String saveExpenditure(
            @RequestParam("batchID") Integer batchID,
            @RequestParam(value = "expenceIDs") List<Integer> expenceIDs,
            @RequestParam(value = "expenditureDate", required = false) String expenditureDate,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        expenditureService.definexpenditure(batchID, expenceIDs, expenditureDate, request);
        return "redirect:/expenditures";
    }

    @GetMapping("/expenditures")
    public String showExpenditureList(Model model,@RequestParam(value = "batchID" , required = false)Integer batchID){
        List<Batch> batches = batchService.getAllBatches();
        model.addAttribute("batches", batches);

        List<Expenditure> expenditures ;
        if(batchID != null){
            expenditures = expenditureService.getExpendituresbybatchID(batchID);
            model.addAttribute("selectedBatchID", batchID);

        }else{
            expenditures = expenditureService.getAllExpenditures();
        }
        model.addAttribute("expenditures", expenditures);
        
        return "expenditurelist";
    }

}
