package com.peanutbutter.peanutbutter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.model.Location;
import com.peanutbutter.peanutbutter.repository.LocationRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public void defineLocation(Location location){
        locationRepository.save(location);
    }

    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    public void updateLocation(Location location){
        locationRepository.save(location);
    }

    

}
