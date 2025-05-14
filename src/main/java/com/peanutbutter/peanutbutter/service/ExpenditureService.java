package com.peanutbutter.peanutbutter.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.model.Batch;
import com.peanutbutter.peanutbutter.model.Expence;
import com.peanutbutter.peanutbutter.model.Expenditure;
import com.peanutbutter.peanutbutter.repository.ExpenditureRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ExpenditureService {

    @Autowired
    private BatchService batchService;

    @Autowired
    private ExpenceService expenceService;

    @Autowired
    private ExpenditureRepository expenditureRepository;

    public void definexpenditure(int batchID, List<Integer> expenceIDs, HttpServletRequest request){
        if(expenceIDs != null){
            Batch batch = batchService.getBatchByID(batchID);
            for(int expenceID : expenceIDs){
                String  paramName = "amount_" + expenceID;
                String amountStr = request.getParameter(paramName);

                if(amountStr !=null && !amountStr.isEmpty()){
                    BigDecimal amountSpent = new BigDecimal(amountStr);

                    Expence expence = expenceService.getExpenceByID(expenceID);

                    Expenditure expenditure = new Expenditure();
                    expenditure.setBatch(batch);
                    expenditure.setExpence(expence);
                    expenditure.setAmountSpent(amountSpent);
                    expenditure.setExpenditureDate(LocalDate.now());
                    
                    expenditureRepository.save(expenditure);
                }
            }
        }
        
    }

    public List<Expenditure> getAllExpenditures(){
        return expenditureRepository.findAll();
    }

    public List<Expenditure>  getExpendituresbybatchID(int batchID){
        return expenditureRepository.findByBatchID(batchID);
    }

    public void updateExpenditure(Expenditure expenditure){
        expenditureRepository.save(expenditure);
    }

}
