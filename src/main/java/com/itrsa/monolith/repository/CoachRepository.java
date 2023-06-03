package com.itrsa.monolith.repository;

import com.itrsa.monolith.entity.Coach;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends CrudRepository<Coach, Long> {
    Coach findByDni(String dni);
}
