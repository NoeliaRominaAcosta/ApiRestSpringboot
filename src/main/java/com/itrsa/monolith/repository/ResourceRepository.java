package com.itrsa.monolith.repository;


import com.itrsa.monolith.entity.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends CrudRepository<Resource, Long> {
    Resource findByName(String name);
}
