package com.peanutbutter.peanutbutter.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.peanutbutter.peanutbutter.model.Customer;
import com.peanutbutter.peanutbutter.model.Payment;
import com.peanutbutter.peanutbutter.model.Product;
import com.peanutbutter.peanutbutter.model.Sales;
import com.peanutbutter.peanutbutter.repository.SalesRepository;
import com.peanutbutter.peanutbutter.service.CustomerService;
import com.peanutbutter.peanutbutter.service.PaymentService;
import com.peanutbutter.peanutbutter.service.ProductService;
import com.peanutbutter.peanutbutter.service.SalesService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SalesController {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private SalesService salesService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CustomerService customerService;


    @GetMapping("/salesDashboard")
    public String showSalesDash(Model model){
         List<Sales> salesList = salesRepository.findAllWithProducts();
        model.addAttribute("salesList", salesList);
        return "salesdashboard";
    }


    @GetMapping("/salesOrder")
    public String showSalesOrderForm(Model model){
        List<Customer> customers = customerService.getAllCuatomers();
        model.addAttribute("customers", customers);

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "salesorder";
    }

    @PostMapping("/saveSalesOrder")
    public String saveSalesOrder(HttpServletRequest request){
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        Customer customer = customerService.getCustomerByID(customerID);

        // Get sales order date from form
        String salesOrderDateStr = request.getParameter("salesOrderDate");
        LocalDate salesOrderDate = (salesOrderDateStr != null && !salesOrderDateStr.isEmpty())
            ? LocalDate.parse(salesOrderDateStr)
            : LocalDate.now();

        

        Map<Integer,Integer> productQuantityMap = new HashMap<>();
        String[] selectedProductIDs = request.getParameterValues("product");
        if(selectedProductIDs != null){
            for(String productIDstr: selectedProductIDs){
                int productID = Integer.parseInt(productIDstr);
                int quantity = Integer.parseInt(request.getParameter("quantity_" + productID));
                productQuantityMap.put(productID,quantity );
            }
        }
        salesService.createSalesOrder(customer, salesOrderDate,  productQuantityMap);
        return "redirect:salesOrder";
    }

    @GetMapping("/salesDelivery")
    public String showDeliveryForm(Model model){
        List<Payment> payments = paymentService.getAllPaymentTypes();
        model.addAttribute("payments", payments);

        List<Sales> salesList = salesService.getPendingSales();
        model.addAttribute("salesList", salesList);
        return "salesdelivery";
    }

    @PostMapping("/saveSaleDelivery")
    public String saveSaleDelivered(@RequestParam long salesid,
                                    @RequestParam(value = "amountPaid", required = false) BigDecimal amountPaid,
                                    @RequestParam(required = false) BigDecimal deliveryCost,
                                    @RequestParam int paymentID,
                                    @RequestParam(value = "deliveryDate", required = false) String deliveryDateStr) {
        if (amountPaid == null) {
            amountPaid = BigDecimal.ZERO;
        }
        LocalDate deliveryDate = (deliveryDateStr != null && !deliveryDateStr.isEmpty())
            ? LocalDate.parse(deliveryDateStr)
            : LocalDate.now();
        salesService.processDelivery(salesid, amountPaid, deliveryCost, paymentID, deliveryDate);
        return "redirect:/salesDelivery";
    }

}
