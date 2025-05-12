package com.peanutbutter.peanutbutter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.model.Unit;
import com.peanutbutter.peanutbutter.repository.UnitRepository;

@Service
public class UnitService {

    @Autowired
    private UnitRepository unitRepository;

    public void defineUnit(Unit unit){
        unitRepository.save(unit);
    }

    public List<Unit> getAllUnits(){
        return unitRepository.findAll();
    }

    public void updateUnit(Unit unit){
        unitRepository.save(unit);
    }

    public void deleteunitByid(int unitID){
        unitRepository.deleteById(unitID);
    }

}
