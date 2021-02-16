package com.propify.challenge;

import com.propify.challenge.model.dto.PropertyReport;
import com.propify.challenge.model.dto.PropertyType;
import com.propify.challenge.repository.AddressRepository;
import com.propify.challenge.repository.PropertyRepository;
import com.propify.challenge.service.AlertService;
import com.propify.challenge.service.PropertyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PropertyServiceTest {

    @Autowired
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
        Mono<PropertyReport> propertyReportMono = propertyService.propertyReport();
        StepVerifier.create(propertyReportMono)
                .expectNextMatches(propertyReport ->
                    propertyReport.getAverageRentPrice() >= 0d &&
                            propertyReport.getQuantityPerType().containsKey(PropertyType.CONDOMINIUM) &&
                            propertyReport.getIllinoisQuantity() >= 0
                )
                .verifyComplete();
    }

}
