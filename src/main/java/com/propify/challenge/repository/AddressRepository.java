package com.propify.challenge.repository;

import com.propify.challenge.model.dao.AddressDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends ReactiveCrudRepository<AddressDao, Integer> {
}
