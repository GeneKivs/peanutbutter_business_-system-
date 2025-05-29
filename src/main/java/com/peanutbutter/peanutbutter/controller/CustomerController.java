package com.peanutbutter.peanutbutter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.peanutbutter.peanutbutter.model.Customer;
import com.peanutbutter.peanutbutter.model.Location;
import com.peanutbutter.peanutbutter.service.CustomerService;
import com.peanutbutter.peanutbutter.service.LocationService;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LocationService locationService;

    @GetMapping("/customerDashboard")
    public String showCustomerDashboard(Model model){
        List <Customer> customers = customerService.getAllCuatomers();
        model.addAttribute("customers", customers);
        return "customerdashboard";
         
    }

    @GetMapping("/customerdef")
    public String showCustomerdefForm(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        List <Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        return "customerdef";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes){
        customerService.defineCustomer(customer);

        return "redirect:customerdef";
    }

    @GetMapping("/customers")
    public String showCustomerList(Model model){
        List <Customer> customers = customerService.getAllCuatomers();
        model.addAttribute("customers", customers);
        return "customerlist";
    }


}
