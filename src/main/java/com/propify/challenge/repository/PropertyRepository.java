package com.propify.challenge.repository;

import com.propify.challenge.model.dao.PropertyDao;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PropertyRepository extends ReactiveCrudRepository<PropertyDao, Integer>  {

    @Query(
            value = "SELECT * FROM property WHERE rent_price >= :minRentPrice and rent_price <= :maxRentPrice"
    )
    Flux<PropertyDao> search(String minRentPrice, String maxRentPrice);
}
