package com.propify.challenge.service;

import com.propify.challenge.model.dao.AddressDao;
import com.propify.challenge.model.dao.PropertyDao;
import com.propify.challenge.model.dto.Address;
import com.propify.challenge.model.dto.Property;
import com.propify.challenge.model.dto.PropertyReport;
import com.propify.challenge.model.dto.PropertyType;
import com.propify.challenge.repository.AddressRepository;
import com.propify.challenge.repository.PropertyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.EnumMap;
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


    public Flux<Property> search(
            String minRentPrice,
            String maxRentPrice
    ) {
        return propertyRepository
                .search(minRentPrice, maxRentPrice)
                .flatMap(propertyDao2propertyDto)
                .flatMap(property ->
                        addressRepository
                                .findById(property.getAddress().getId())
                                .flatMap(addressDao2addressDto)
                                .flatMap(address -> {
                                    property.setAddress(address);
                                    return Mono.just(property);
                                })
                );
    }

    public Mono<Property> findById(int id) {
        return propertyRepository
                .findById(id)
                .flatMap(propertyDao2propertyDto)
                .flatMap(property ->
                        addressRepository
                                .findById(property.getAddress().getId())
                                .flatMap(addressDao2addressDto)
                                .flatMap(address -> {
                                    property.setAddress(address);
                                    return Mono.just(property);
                                })
                );
    }

    public Mono<Property> insert(Property property) {
        return addressRepository
                .save(addressDto2addressDao(property.getAddress()))
                .flatMap(addressDAO -> {
                    PropertyDao propertyDAO = propertyDto2propertyDao(property);
                    propertyDAO.setAddressId(addressDAO.getId());
                    return propertyRepository
                            .save(propertyDAO)
                            .flatMap(propertyDao2propertyDto)
                            .doOnSuccess(property1 -> log.info(property1.getId()));
                });
    }

    public Mono<Property> update(Property property) {
        return addressRepository
                .save(addressDto2addressDao(property.getAddress()))
                .flatMap(addressDAO -> {
                    PropertyDao propertyDAO = propertyDto2propertyDao(property);
                    propertyDAO.setAddressId(addressDAO.getId());
                    return propertyRepository
                            .save(propertyDAO)
                            .flatMap(propertyDao2propertyDto)
                            .doOnSuccess(property1 -> log.info(property1.getId()));
                });
    }

    public Mono<Void> delete(Property property) {
        return propertyRepository
                .deleteById(property.getId())
                .doOnSuccess(unused -> log.info(property.getId()))
                .doOnSuccess(unused -> alertService.sendPropertyDeletedAlert(property.getId()));
        // TODO: Sending the alert should be non-blocking (asynchronous). DONE
        //  Extra points for only sending the alert when/if the transaction is committed. DONE
    }

    public Mono<PropertyReport> propertyReport() {
        return propertyRepository
                .findAll()
                .flatMap(propertyDao2propertyDto)
                .flatMap(property ->
                        addressRepository
                                .findById(property.getAddress().getId())
                                .flatMap(addressDao2addressDto)
                                .flatMap(address -> {
                                    property.setAddress(address);
                                    return Mono.just(property);
                                })
                )
                .collectList()
                .flatMap(listOfProperties -> {
                    PropertyReport propertyReport = new PropertyReport();
                    propertyReport.setTotalQuantity(0);
                    EnumMap<PropertyType, Integer> map = new EnumMap<>(PropertyType.class);
                    map.put(PropertyType.CONDOMINIUM, 0);
                    map.put(PropertyType.MULTI_FAMILY, 0);
                    map.put(PropertyType.SINGLE_FAMILY, 0);
                    map.put(PropertyType.TOWNHOUSE, 0);
                    propertyReport.setQuantityPerType(map);
                    double avg = 0d;
                    int times = 1;

                    for (Property property : listOfProperties) {
                        // Calculate total quantity
                        propertyReport.setTotalQuantity(propertyReport.getTotalQuantity() + 1);

                        // Calculate the quantity of each type, 0 if there is no properties.
                        propertyReport.getQuantityPerType().entrySet().forEach(propertyTypeIntegerEntry -> {
                            if(propertyTypeIntegerEntry.getKey() == property.getType()) {
                                propertyTypeIntegerEntry.setValue(propertyTypeIntegerEntry.getValue() + 1);
                            }
                        });

                        // Calculate the average rent price (exclude the properties without rent price or with rent price = 0)
                        if (property.getRentPrice() != null) {
                            avg = avg + (property.getRentPrice().doubleValue() - avg) / times;
                            times++;
                        }

                        // Calculate the quantity of properties in the state of Illinois (IL)
                        if (property.getAddress().getState().equals("IL")) {
                            propertyReport.setIllinoisQuantity(propertyReport.getIllinoisQuantity() + 1);
                        }
                    }
                    propertyReport.setAverageRentPrice(avg);

                    return Mono.just(propertyReport);
                });
    }
}
