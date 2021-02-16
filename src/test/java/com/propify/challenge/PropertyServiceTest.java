package com.propify.challenge;

import com.propify.challenge.repository.AddressRepository;
import com.propify.challenge.repository.PropertyRepository;
import com.propify.challenge.service.AlertService;
import com.propify.challenge.service.PropertyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    PropertyService propertyService;

    @MockBean
    PropertyRepository propertyRepository;

    @MockBean
    AddressRepository addressRepository;

    @MockBean
    AlertService alertService;

    // TODO: add at least 3 tests to the method propertyService.propertyReport()
    @Test
    public void testPropertyReport() {

    }

}
