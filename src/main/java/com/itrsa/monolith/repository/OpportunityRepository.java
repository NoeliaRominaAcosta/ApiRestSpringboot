package com.itrsa.monolith.repository;

import com.itrsa.monolith.entity.Opportunity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunityRepository extends CrudRepository<Opportunity, Long> {
    Opportunity findByCode(String code);
    void deleteByCode(String code);
}