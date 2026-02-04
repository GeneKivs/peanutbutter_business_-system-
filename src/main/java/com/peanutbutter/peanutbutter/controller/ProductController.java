package com.peanutbutter.peanutbutter.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @GetMapping("/inventory")
    public String showinventoryDash(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "inventorydashboard";
    }

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
        return "redirect:/inventory";
    }

   

    @GetMapping("/productpricedef")
    public String showProductPricedefinition(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "productpricedef";
    }

    @PostMapping("/saveProductPrice")
    public String saveProductPrice(@RequestParam("productID")Integer productID,
                                    @RequestParam("pricePerTin")String pricePerTinStr,
                                    RedirectAttributes redirectAttributes){

        Product product = productService.getProductByID(productID);

        if(product != null){
            try{
                BigDecimal pricePerTin = new BigDecimal(pricePerTinStr);
            //update the productprice
            product.setPricePerTin(pricePerTin);
            productService.updateProduct(product);
            redirectAttributes.addFlashAttribute("successMessage", "Product price updated successfully.");
            }catch(NumberFormatException e){
                redirectAttributes.addFlashAttribute("errorMessage", "Invalid price format.");
            }
        }else{
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found.");
        }
        return "redirect:/inventory";
    }

     @GetMapping("/productEdit/{productID}")
    public String showProductEditfForm(Model model, @PathVariable("productID")int productID){
        Product product = productService.getProductByID(productID);
        model.addAttribute("product", product);

        List<Unit> units = unitService.getAllUnits();
        model.addAttribute("units", units);
        return "productUpdate";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(){
        return  "redirect:/inventory";
    }

}
