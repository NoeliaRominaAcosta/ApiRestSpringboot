package com.itrsa.monolith.service;
import com.itrsa.monolith.dto.EmployeeDTO;
import com.itrsa.monolith.dto.EmployeeShorterDTO;
import com.itrsa.monolith.dto.ResourceDTO;

public interface EmployeeService {
    Iterable<EmployeeDTO> findAll();

    void save(EmployeeDTO dto, String dniCoach);

    EmployeeDTO findByDni(String dni);

    void edit(EmployeeDTO dto);

    void deleteByDni(String dni);
    void addSkill(Long skillId, Long employeeId);

    void addRole(Long roleId, String employeeDni);

    void addResource (String employeeDni, Long resourceId);

    void addOpportunity (String employeeDni, Long opportunityId);

    Iterable<ResourceDTO> listResources(String dni);

    Iterable<EmployeeShorterDTO> listEmployeesBySkill(String skillCode);
}
