package com.itrsa.monolith.service;
import com.itrsa.monolith.dto.EmployeeDTO;
import com.itrsa.monolith.dto.ResourceDTO;

public interface EmployeeService {
    Iterable<EmployeeDTO> findAll();

    void save(EmployeeDTO dto, String dniCoach);

    EmployeeDTO findByDni(String dni);

    void edit(EmployeeDTO dto);

    void deleteByDni(String dni);
    void addSkill(Long skillId, Long employeeId);

    void addRole(Long roleId, String employeeDni);

    Iterable<ResourceDTO> listResources(String dni);
}
