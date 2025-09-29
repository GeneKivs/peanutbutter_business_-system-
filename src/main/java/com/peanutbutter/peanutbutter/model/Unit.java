package com.peanutbutter.peanutbutter.model;

import jakarta.persistence.*;

@Entity
@Table(name = "units")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int unitID;
    
    private String unitName;

    private String unitAbbriviation;
    
    private String description;

    public int  getUnitID(){
        return unitID;
    }

    public void setUnitID(int unitID){
        this.unitID = unitID;
    }

    public String getUnitName(){
        return unitName;
    }

    public void setUnitName(String unitName){
        this.unitName = unitName;
    }

    public String getUnitAbbriviation(){
        return unitAbbriviation;
    }

    public void setUnitAbbriviation(String unitAbbriviation){
        this.unitAbbriviation = unitAbbriviation;
    }

    public  String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

}
