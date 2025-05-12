package com.peanutbutter.peanutbutter.model;

import jakarta.persistence.*;



@Entity
@Table(name = "expences")
public class Expence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expenceID;
    private String expenceName;
    private String description;

    public int getExpenceID(){
        return expenceID;
    }

    public void setExpenceID(int expenceID){
        this.expenceID = expenceID;
    }

    public String getExpenceName(){
        return expenceName;
    }

    public void setExpenceName(String expenceName){
        this.expenceName = expenceName;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
