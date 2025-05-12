package com.peanutbutter.peanutbutter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.peanutbutter.peanutbutter.model.Product;
import com.peanutbutter.peanutbutter.model.Unit;
import com.peanutbutter.peanutbutter.service.ProductService;
import com.peanutbutter.peanutbutter.service.UnitService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UnitService unitService;

    @GetMapping("/productdef")
    public String showProductdefForm(Model model){
        Product product = new Product();
        model.addAttribute("product", product);

        List<Unit> units = unitService.getAllUnits();
        model.addAttribute("units", units);
        return "productdef";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product")Product product, RedirectAttributes redirectAttributes){
        
        productService.defineProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String showProductList(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "productlist";
    }

}
