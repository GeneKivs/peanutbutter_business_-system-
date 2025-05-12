package com.peanutbutter.peanutbutter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.model.Expence;
import com.peanutbutter.peanutbutter.repository.ExpenceRepository;

@Service
public class ExpenceService {

    @Autowired
    private ExpenceRepository expenceRepository;

    public void defineExpence(Expence expence){
        expenceRepository.save(expence);
    }
    
    public Expence getExpenceByID(int expenceID){
        return expenceRepository.findById(expenceID).orElse(null);
    }
    public List<Expence> getALlExpences(){
        return expenceRepository.findAll();
    }

    public void updateExpences(Expence expence){
        expenceRepository.save(expence);
    }

    public void deleteExpenceByID(int expenceID){
        expenceRepository.deleteById(expenceID);
    }

}
