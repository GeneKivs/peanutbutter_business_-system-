package com.peanutbutter.peanutbutter.model;

import java.time.LocalDate;

import com.peanutbutter.peanutbutter.base.Auditable;

import jakarta.persistence.*;

@Entity
@Table(name = "journal_entry")
public class JournalEntry extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate entryDate;

    private String description;

    private String referenceType;

    private Long referenceID;


    public Long getID(){
        return id;
    }

    public void setID(Long id){
        this.id = id;
    }

    public LocalDate getEntryDate(){
        return entryDate;
    }

    public void setEntryDate(LocalDate date){
        this.entryDate = date;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getReferenceType(){
        return referenceType;
    }

    public void setReferenceType(String referenceType){
        this.referenceType = referenceType;
    }

    public Long getReferenceID(){
        return referenceID;
    }

    public void setReferenceID(Long id){
        this.referenceID = id;
    }



    

}
