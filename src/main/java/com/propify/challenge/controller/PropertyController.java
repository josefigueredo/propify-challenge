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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Properties", description = "")
@RequestMapping("/properties")
@Log4j2
public class PropertyController {

    PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    // API endpoints for CRUD operations on entities of type Property

    @Operation(summary = "Search properties by min and max rent price")
    public Flux<PropertyDao> search(String minRentPrice, String maxRentPrice) {
        return propertyService.search(minRentPrice, maxRentPrice);
    }

    public Mono<Property> findById(int id) {
        // It will be a dto later, I need to be able to build first. Same with the others.

        return propertyService.findById(id);
    }

    public Mono<Property> insert(Property property) {
        // TODO: Property attributes must be validated

        return propertyService.insert(property);
    }

    public Mono<Property> update(Property property) {
        // TODO: Property attributes must be validated

        return propertyService.update(property);
    }

    public Mono<Void> delete(int id) {
        return propertyService.delete(id);
    }

    public Mono<PropertyReport> report() {
        return propertyService.propertyReport();
    }
}
