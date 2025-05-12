package com.peanutbutter.peanutbutter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.peanutbutter.peanutbutter.model.Product;
import com.peanutbutter.peanutbutter.service.BatchService;
import com.peanutbutter.peanutbutter.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BatchReceiveController {
    @Autowired
    private ProductService productService;

    @Autowired
    private BatchService batchService;

    @GetMapping("/receiveBatch")
    public  String showBatchreceiveform(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products",products);
        return "batchreceive";
    }

    @PostMapping("/saveBatch")
    public String saveBatch(HttpServletRequest request){
        //extract the peanut quantity paid from form fields
        int peanutQuantity = Integer.parseInt(request.getParameter("peanutQuantity"));
        double amountPaid = Double.parseDouble(request.getParameter("amountPaid"));

        //get all selected product IDs 
        String[] productIds = request.getParameterValues("product");

        //call the service method to define the batch and save it to the database
        batchService.defineBatch(peanutQuantity, amountPaid, productIds, request);
        return "redirect:/receiveBatch";
    }

}
