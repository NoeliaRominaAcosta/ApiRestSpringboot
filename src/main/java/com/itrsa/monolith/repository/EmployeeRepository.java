package com.itrsa.monolith.repository;

import com.itrsa.monolith.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findByDni(String dni);
}
