package com.propify.challenge.controller;

import com.propify.challenge.model.dao.PropertyDao;
import com.propify.challenge.model.dto.Property;
import com.propify.challenge.model.dto.PropertyReport;
import com.propify.challenge.service.PropertyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/properties")
@Log4j2
public class PropertyController {

    PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    // API endpoints for CRUD operations on entities of type Property

    public Flux<PropertyDao> search(String minRentPrice, String maxRentPrice) {
        return propertyService.search(minRentPrice, maxRentPrice);
    }

    public Mono<PropertyDao> findById(int id) {
        // It will be a dto later, I need to be able to build first. Same with the others.

        return propertyService.findById(id);
    }

    public void insert(Property property) {
        // TODO: Property attributes must be validated

        PropertyDao propertyDao = new PropertyDao();
        propertyService.insert(propertyDao);
    }

    public void update(Property property) {
        // TODO: Property attributes must be validated

        PropertyDao propertyDao = new PropertyDao();
        propertyService.update(propertyDao);
    }

    public void delete(int id) {
        propertyService.delete(id);
    }

    public PropertyReport report() {
        return propertyService.propertyReport();
    }
}
