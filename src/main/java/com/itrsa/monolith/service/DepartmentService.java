package com.itrsa.monolith.service;

import com.itrsa.monolith.dto.DepartmentDTO;
import com.itrsa.monolith.dto.RoleDTO;

public interface DepartmentService {
    Iterable<DepartmentDTO> findAll();

    void save(DepartmentDTO dto);

    DepartmentDTO findDepartmentById(Long id);

    void edit(DepartmentDTO dto, Long id);

    void deleteDepartmentById(Long id);

    Iterable<RoleDTO> listRoles(Long id);
}
