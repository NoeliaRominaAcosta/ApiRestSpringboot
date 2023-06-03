package com.itrsa.monolith.repository;

import com.itrsa.monolith.entity.Assigner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignerRepository extends CrudRepository<Assigner, Long> {
    Assigner findBydni(String dni);

    void deleteByDni(String dni);
}
