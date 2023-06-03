package com.itrsa.monolith.service;
import com.itrsa.monolith.dto.CoachDTO;
import com.itrsa.monolith.dto.EmployeeDTO;


public interface CoachService {
    Iterable<CoachDTO> findAll();

    CoachDTO findByDni(String dni);

    void save(CoachDTO dto);

    void update(CoachDTO dto);

    void deleteByDni(String dni);

    Iterable<EmployeeDTO> listEmployees(String dni);

}
