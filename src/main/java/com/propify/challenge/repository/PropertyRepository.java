package com.propify.challenge.repository;

import com.propify.challenge.model.Property;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends ReactiveCrudRepository<Property, Integer>  {
}
