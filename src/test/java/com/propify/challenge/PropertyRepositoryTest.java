package com.propify.challenge;

import com.propify.challenge.model.dto.Property;
import com.propify.challenge.model.dto.PropertyType;
import com.propify.challenge.repository.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.math.BigDecimal;

@SpringJUnitConfig
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
})
public class PropertyRepositoryTest {

    @Autowired
    PropertyRepository propertyRepository;

    @Test
    public void testInsert() {
        var property = new Property();
        property.setRentPrice(new BigDecimal("3000.99"));
        property.setType(PropertyType.MULTI_FAMILY);

        // propertyRepository.save(property);

        // TODO: add assertions
    }

    @Test
    public void testFindById() {
        var property = propertyRepository.findById(1);

        assert property.block() != null;
        // TODO: add assertions
    }

    // TODO: Add more tests
}
