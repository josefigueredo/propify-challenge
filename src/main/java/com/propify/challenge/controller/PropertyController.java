package com.propify.challenge.controller;

import com.propify.challenge.model.dto.Property;
import com.propify.challenge.model.dto.PropertyReport;
import com.propify.challenge.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @Operation(summary = "Inserts a property")
    @PostMapping
    public Mono<Property> insert(@RequestBody Property property) {
        // TODO: Property attributes must be validated
        return propertyService.findById(property.getId())
                .switchIfEmpty(
                        Mono.error(
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "No property found for the id " + property.getId())
                        )
                )
                .flatMap(
                        property1 -> propertyService.insert(property)
                );
    }

    @Operation(summary = "Updates a property")
    @PutMapping(value = "{id}")
    public Mono<Property> update(@RequestBody Property property) {
        // TODO: Property attributes must be validated
        return propertyService.findById(property.getId())
                .switchIfEmpty(
                        Mono.error(
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "No property found for the id " + property.getId())
                        )
                )
                .flatMap(
                        property1 -> propertyService.update(property)
                );
    }

    @Operation(summary = "Deletes a property")
    @DeleteMapping(value = "{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        return propertyService
                .findById(id)
                .switchIfEmpty(
                        Mono.error(
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "No property found for the id " + id)
                        )
                )
                .doOnSuccess(
                        property -> propertyService.delete(property).subscribe()
                )
                .then();
    }

    @Operation(summary = "Search properties by min and max rent price")
    @GetMapping(value = "filters/searchRentPriceByMinAndMax/{minRentPrice}-{maxRentPrice}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Flux<Property> searchRentPriceByMinAndMax(
            @PathVariable String minRentPrice,
            @PathVariable String maxRentPrice
    ) {

        return propertyService
                .search(minRentPrice, maxRentPrice)
                .switchIfEmpty(
                        Mono.error(
                                new ResponseStatusException(HttpStatus.NOT_FOUND,"No properties found for the range: " + minRentPrice + " to " + maxRentPrice)
                        )
                );
    }

    @Operation(summary = "Finds a property by its id")
    @GetMapping(value = "{id}")
    public Mono<Property> findById(@PathVariable Integer id) {
        return propertyService
                .findById(id)
                .switchIfEmpty(
                        Mono.error(
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "No property found for the id " + id)
                        )
                );
    }

    @Operation(summary = "Generates a report of the properties")
    @GetMapping(value = "report")
    public Mono<PropertyReport> report() {
        return propertyService.propertyReport();
    }
}
