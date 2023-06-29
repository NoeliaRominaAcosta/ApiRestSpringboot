package com.itrsa.monolith.service;

import com.itrsa.monolith.dto.RoleDTO;
import com.itrsa.monolith.dto.RoleDepartmentDTO;

public interface RoleService {

    Iterable<RoleDTO> findAll();

    void save(RoleDTO dto, Long departmentId);

    RoleDTO findRoleById(Long id);

    void edit(RoleDTO dto, Long id);

    void deleteById(Long id);
    void editDepartment(Long departmentId, Long rolId);

    Iterable<RoleDepartmentDTO> findRoleDepartments();
}
