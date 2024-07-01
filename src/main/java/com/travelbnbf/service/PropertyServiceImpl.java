package com.travelbnbf.service;

import com.travelbnbf.entity.Property;
import com.travelbnbf.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PropertyServiceImpl implements PropertyService{


    private PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public List<Property> searchProperty(String name) {
        List<Property> properties = propertyRepository.searchPropertyByLocation(name);
        return properties;
    }
}
