package com.propify.challenge;

import com.propify.challenge.controller.PropertyController;
import com.propify.challenge.model.dao.PropertyDao;
import com.propify.challenge.model.dto.Property;
import com.propify.challenge.model.dto.PropertyType;
import com.propify.challenge.repository.PropertyRepository;
import com.propify.challenge.service.PropertyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = PropertyController.class)
@Import(PropertyService.class)
class PropertyControllerTest {

    @MockBean
    PropertyRepository repository;

    @Autowired
    private WebTestClient webClient;

    @Test
    void testCreateEmployee() {
        Property property = new Property();
        property.setRentPrice(new BigDecimal(1000.23));
        property.setType(PropertyType.MULTI_FAMILY);
        property.setCode("AAAAACCCCC");
        property.setEmailAddress("josefigueredo@gmail.com");

        PropertyDao propertyDao = new PropertyDao();

        Mockito.when(repository.save(any())).thenReturn(Mono.just(propertyDao));

        webClient.post()
                .uri("/")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(property))
                .exchange()
                .expectStatus().isCreated();

        Mockito.verify(repository, times(1)).save(propertyDao);
    }
}
