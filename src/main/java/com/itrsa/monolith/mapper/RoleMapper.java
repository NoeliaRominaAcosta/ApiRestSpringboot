package com.itrsa.monolith.mapper;

import com.itrsa.monolith.dto.RoleDTO;
import com.itrsa.monolith.dto.RoleDepartmentDTO;
import com.itrsa.monolith.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RoleMapper {
    @Mapping(target="name", source="entity.name")
    @Mapping(target="description", source="entity.description")
    @Mapping(target="seniority", source="entity.seniority")
    RoleDTO toRoleDTO(Role entity);

    @Mapping(target="name", source="dto.name")
    @Mapping(target="description", source="dto.description")
    @Mapping(target="seniority", source="dto.seniority")
    @Mapping(target = "id", ignore = true)
    Role toRole(RoleDTO dto);

    Iterable<RoleDTO> toList(Iterable<Role> roles);

    @Mapping(target="name", source="role.name")
    @Mapping(target="description", source="role.description")
    @Mapping(target="seniority", source="role.seniority")
    @Mapping(target="nameDepartment", source="role.department.name")
    RoleDepartmentDTO toRoleDepartmentDTO(Role role);

    Iterable<RoleDepartmentDTO> toRoleDepartmentList(Iterable<Role> roles);



}
