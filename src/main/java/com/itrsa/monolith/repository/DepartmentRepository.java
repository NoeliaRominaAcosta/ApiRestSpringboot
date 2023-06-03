package com.itrsa.monolith.repository;

import com.itrsa.monolith.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Department findDepartmentById(Long id);
}
