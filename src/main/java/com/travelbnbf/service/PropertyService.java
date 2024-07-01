package com.travelbnbf.service;

import com.travelbnbf.entity.Property;

import java.util.List;
public interface PropertyService {
    List<Property> searchProperty(String name);
}
