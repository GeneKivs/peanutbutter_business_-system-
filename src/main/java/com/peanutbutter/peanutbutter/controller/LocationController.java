package com.peanutbutter.peanutbutter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.peanutbutter.peanutbutter.model.Location;
import com.peanutbutter.peanutbutter.service.LocationService;

@Controller
public class LocationController {

    @Autowired
    private LocationService locationService;
    

    @GetMapping("/locationdef")
    public String showLocationform(Model model){
        Location location = new Location();
        model.addAttribute("location", location);
        return "locationdef";
    }

    @PostMapping("/saveLocation")
    public String saveLocation(@ModelAttribute("location")Location location, RedirectAttributes redirectAttributes){
        System.out.println(location);
        locationService.defineLocation(location);
        redirectAttributes.addFlashAttribute("successmessage","location added succesfully ");
        return "redirect:/locations";
    }

    @GetMapping("/locations")
    public String showLocationList(Model model){
        List <Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        return "locationlist";
    }

    @GetMapping("/locationEdit/{locationID}")
    public String showLocationEditForm(@PathVariable("locationID")int locationID, Model model){
        Location location = locationService.getLocationById(locationID);
        model.addAttribute("location", location);
        return "locationEdit";
    }

    @PostMapping("/updateLocation")
    public String updateLocation(@ModelAttribute("location")Location location, RedirectAttributes redirectAttributes){
        locationService.updateLocation(location);
        return "redirect:/locations";
    }

}
