package com.itrsa.monolith.service;

import com.itrsa.monolith.entity.Department;
import com.itrsa.monolith.mapper.RoleMapper;
import com.itrsa.monolith.dto.RoleDTO;
import com.itrsa.monolith.entity.Role;
import com.itrsa.monolith.repository.DepartmentRepository;
import com.itrsa.monolith.repository.RoleRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceBusiness implements RoleService{
    @Autowired
    private RoleRepository repository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public RoleServiceBusiness(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<RoleDTO> findAll() {
        var mapper = Mappers.getMapper(RoleMapper.class);
        return mapper.toList(repository.findAll());
    }

    @Override
    public void save(RoleDTO dto, Long departmentId) {
        var mapper = Mappers.getMapper(RoleMapper.class);
        Role rolEntity = mapper.toRole(dto);
        Department departmentEntity = departmentRepository.findDepartmentById(departmentId);
        rolEntity.setDepartment(departmentEntity);

        repository.save(rolEntity);
    }

    @Override
    public RoleDTO findRoleById(Long id) {
        var mapper = Mappers.getMapper(RoleMapper.class);
        return mapper.toRoleDTO(repository.findRoleById(id));
    }

    @Override
    public void edit(RoleDTO dto, Long id) {
        Role role = repository.findRoleById(id);
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        role.setSeniority(dto.getSeniority());
        repository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        Role role = repository.findRoleById(id);
        repository.delete(role);
    }

    @Override
    public void editDepartment(Long departmentId, Long roleId) {
        Department departmentEntity = departmentRepository.findDepartmentById(departmentId);
        Role roleEntity = repository.findRoleById(roleId);

        roleEntity.setDepartment(departmentEntity);

        repository.save(roleEntity);
    }

}
