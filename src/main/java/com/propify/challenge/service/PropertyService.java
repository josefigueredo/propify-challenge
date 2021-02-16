package com.propify.challenge.service;

import com.propify.challenge.model.dao.AddressDao;
import com.propify.challenge.model.dao.PropertyDao;
import com.propify.challenge.model.dto.Address;
import com.propify.challenge.model.dto.Property;
import com.propify.challenge.model.dto.PropertyReport;
import com.propify.challenge.repository.AddressRepository;
import com.propify.challenge.repository.PropertyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Log4j2
public class PropertyService {

    AlertService alertService;
    AddressRepository addressRepository;
    PropertyRepository propertyRepository;

    public PropertyService(
            AlertService alertService,
            AddressRepository addressRepository,
            PropertyRepository propertyRepository
    ) {
        this.alertService = alertService;
        this.addressRepository = addressRepository;
        this.propertyRepository = propertyRepository;
    }

    Function<PropertyDao, Mono<Property>> propertyDao2propertyDto = (PropertyDao propertyDao) -> {
        Property property = new Property();
        BeanUtils.copyProperties(propertyDao, property);
        Address address = new Address();
        address.setId(propertyDao.getAddressId());
        property.setAddress(address);
        return Mono.just(property);
    };

    private PropertyDao propertyDto2propertyDao(Property property) {
        PropertyDao propertyDao = new PropertyDao();
        BeanUtils.copyProperties(property, propertyDao);
        return propertyDao;
    }

    Function<AddressDao, Mono<Address>> addressDao2addressDto = (AddressDao addressDao) -> {
        Address address = new Address();
        BeanUtils.copyProperties(addressDao, address);
        return Mono.just(address);
    };

    private AddressDao addressDto2addressDao(Address address) {
        AddressDao addressDao = new AddressDao();
        BeanUtils.copyProperties(address, addressDao);
        return addressDao;
    }

    public Flux<PropertyDao> search(String minRentPrice, String maxRentPrice) {
        return propertyRepository.search(minRentPrice, maxRentPrice);
    }

    public Mono<Property> findById(int id) {
        return propertyRepository
                .findById(id)
                .flatMap(propertyDao2propertyDto);
    }

    public Mono<Property> insert(Property property) {
        return propertyRepository
                .save(propertyDto2propertyDao(property))
                .flatMap(propertyDao2propertyDto);
    }

    public Mono<Property> update(Property property) {
        return propertyRepository
                .save(propertyDto2propertyDao(property))
                .flatMap(propertyDao2propertyDto);
    }

    public Mono<Void> delete(int id) {
        return propertyRepository
                .deleteById(id);
        // TODO: Sending the alert should be non-blocking (asynchronous)
        //  Extra points for only sending the alert when/if the transaction is committed
    }

    public Mono<PropertyReport> propertyReport() {
        throw new RuntimeException("Not implemented");
        //var allProperties = propertyMapper.search(null, null);
        //var propertyReport = new PropertyReport();

        // Calculate total quantity
        // propertyReport.totalQuantity =

        // Calculate the quantity of each type, 0 if there is no properties.
        // propertyReport.quantityPerType =

        // Calculate the average rent price (exclude the properties without rent price or with rent price = 0)
        // propertyReport.averageRentPrice =

        // Calculate the quantity of properties in the state of Illinois (IL)
        // propertyReport.illinoisQuantity =

        //return propertyReport;
    }
}
