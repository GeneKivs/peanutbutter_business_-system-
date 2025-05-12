package com.peanutbutter.peanutbutter.model;

import jakarta.persistence.*;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationID;


    @Column(name = "location_Name")
    private String locationName;
    
    public int getLocationID(){
        return locationID;
    }

    public void setLocationID(int locationID){
        this.locationID = locationID;
    }

    public String getLocationName(){
        return locationName;
    }

    public void setLocationName(String locationName){
        this.locationName = locationName;
    }
}
