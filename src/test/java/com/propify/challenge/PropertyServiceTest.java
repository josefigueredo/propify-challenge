package com.propify.challenge;

import com.propify.challenge.model.AddressMapper;
import com.propify.challenge.model.PropertyMapper;
import com.propify.challenge.service.AlertService;
import com.propify.challenge.service.PropertyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    PropertyService propertyService;

    PropertyMapper propertyMapper;

    AddressMapper addressMapper;

    AlertService alertService;

    // TODO: add at least 3 tests to the method propertyService.propertyReport()
    @Test
    public void testPropertyReport() {

    }

}
