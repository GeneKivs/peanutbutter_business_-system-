package com.peanutbutter.peanutbutter.service;

import com.peanutbutter.peanutbutter.model.Expenditure;
import com.peanutbutter.peanutbutter.model.Payment;
import com.peanutbutter.peanutbutter.model.Purchase;
import com.peanutbutter.peanutbutter.model.Sales;

public interface AccountingService {

    void paymentAccounting (Payment payment);
    void purchaseAccounting(Purchase purchase);
    void expenditureAccounting(Expenditure expenditure);
    void salesAccounting(Sales sales,Payment payment);



}
