package com.peanutbutter.peanutbutter.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.model.Batch;
import com.peanutbutter.peanutbutter.model.Expence;
import com.peanutbutter.peanutbutter.model.Expenditure;
import com.peanutbutter.peanutbutter.repository.BatchRepository;
import com.peanutbutter.peanutbutter.repository.ExpenditureRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ExpenditureService {

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private BatchService batchService;

    @Autowired
    private ExpenceService expenceService;

    @Autowired
    private ExpenditureRepository expenditureRepository;

    public void definexpenditure(int batchID, List<Integer> expenceIDs, String expenditureDate, HttpServletRequest request) {
        if (expenceIDs != null) {
            Batch batch = batchService.getBatchByID(batchID);
            LocalDate expDate;
            if (expenditureDate != null && !expenditureDate.isEmpty()) {
                expDate = LocalDate.parse(expenditureDate, DateTimeFormatter.ISO_DATE);
            } else {
                expDate = LocalDate.now();
            }
            for (int expenceID : expenceIDs) {
                String paramName = "amount_" + expenceID;
                String amountStr = request.getParameter(paramName);

                if (amountStr != null && !amountStr.isEmpty()) {
                    BigDecimal amountSpent = new BigDecimal(amountStr);

                    Expence expence = expenceService.getExpenceByID(expenceID);

                    Expenditure expenditure = new Expenditure();
                    expenditure.setBatch(batch);
                    expenditure.setExpence(expence);
                    expenditure.setAmountSpent(amountSpent);
                    expenditure.setExpenditureDate(expDate);

                    expenditureRepository.save(expenditure);

                    // Add the expenditure to the total expenditure in the batch table
                    Batch batchexpend = expenditure.getBatch();

                    BigDecimal getBatchExpenditure = batchexpend.getTotalExpenditure();
                    BigDecimal totalExpence = getBatchExpenditure.add(expenditure.getAmountSpent());

                    batchexpend.setTotalExpenditure(totalExpence);
                    batchRepository.save(batchexpend);
                }
            }
        }
    }

    public List<Expenditure> getAllExpenditures() {
        return expenditureRepository.findAll();
    }

    public List<Expenditure> getExpendituresbybatchID(int batchID) {
        return expenditureRepository.findByBatchID(batchID);
    }

    public void updateExpenditure(Expenditure expenditure) {
        expenditureRepository.save(expenditure);
    }

}
