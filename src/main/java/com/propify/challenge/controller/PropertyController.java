package com.propify.challenge.controller;

import com.propify.challenge.model.Property;
import com.propify.challenge.model.PropertyReport;
import com.propify.challenge.service.PropertyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/properties")
@Log4j2
public class PropertyController {

    PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    // API endpoints for CRUD operations on entities of type Property

    public Collection<Property> search(String minRentPrice, String maxRentPrice) {
        return propertyService.search(minRentPrice, maxRentPrice);
    }

    public Property findById(int id) {
        return propertyService.findById(id);
    }

    public void insert(Property property) {
        // TODO: Property attributes must be validated
        propertyService.insert(property);
    }

    public void update(Property property) {
        // TODO: Property attributes must be validated
        propertyService.update(property);
    }

    public void delete(int id) {
        propertyService.delete(id);
    }

    public PropertyReport report() {
        return propertyService.propertyReport();
    }
}
