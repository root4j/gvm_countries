package com.oracle.graal.demo.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oracle.graal.demo.model.Country;

/**
 *
 * @author rjay
 */
@Repository
public interface CountryRepository extends CrudRepository<Country, String> {
    
}
