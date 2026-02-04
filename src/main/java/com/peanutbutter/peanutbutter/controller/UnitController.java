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

import com.peanutbutter.peanutbutter.model.Unit;
import com.peanutbutter.peanutbutter.service.UnitService;

@Controller
public class UnitController {

    @Autowired
    private UnitService unitService;

    @GetMapping("/unitdef")
    public String showUnitdefform(Model model){
        Unit unit = new Unit();
        model.addAttribute("unit", unit);
        return "unitdef";
    }

    @PostMapping("/saveUnit")
    public String saveUnit(@ModelAttribute("unit")Unit unit, RedirectAttributes redirectAttributes){
        unitService.defineUnit(unit);
        return "redirect:/unitdef";
    }

    @GetMapping("/units")
    public String showUnitList(Model model){
        List <Unit> units = unitService.getAllUnits();
        model.addAttribute("units", units);
        return "unitlist";
    }

    @GetMapping("/unitEdit/{unitID}")
    public String showUnitEditForm(@PathVariable("unitID")int unitID,Model model){
        Unit unit = unitService.getUnitByID(unitID);
        model.addAttribute("unit", unit);
        return "unitEdit";
    }

    @PostMapping("/updateUnit")
    public String updateUnit(@ModelAttribute("unit")Unit unit, RedirectAttributes redirectAttributes){
        unitService.updateUnit(unit);
        return "redirect:/units";
    }


}
