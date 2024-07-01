package com.travelbnbf.controller;

import com.travelbnbf.entity.Property;
import com.travelbnbf.repository.PropertyRepository;
import com.travelbnbf.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    private PropertyRepository propertyRepository;
    private PropertyService propertyService;

    public PropertyController(PropertyRepository propertyRepository, PropertyService propertyService) {
        this.propertyRepository = propertyRepository;
        this.propertyService = propertyService;
    }
    @GetMapping("/search/properties")
    public ResponseEntity<List<Property>> searchProperty(@RequestParam String name){
        List<Property> properties = propertyService.searchProperty(name);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }
}
