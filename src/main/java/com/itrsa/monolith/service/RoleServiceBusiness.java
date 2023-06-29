package com.itrsa.monolith.service;

import com.itrsa.monolith.dto.RoleDepartmentDTO;
import com.itrsa.monolith.entity.Department;
import com.itrsa.monolith.mapper.RoleMapper;
import com.itrsa.monolith.dto.RoleDTO;
import com.itrsa.monolith.entity.Role;
import com.itrsa.monolith.repository.DepartmentRepository;
import com.itrsa.monolith.repository.RoleRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
public class RoleServiceBusiness implements RoleService{
    @Autowired
    private RoleRepository repository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    public RoleServiceBusiness(RoleRepository repository) {
        this.repository = repository;
    }

    @Autowired
    EntityManager entityManager;

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
    @Transactional
    public void edit(RoleDTO dto, Long id) {
        Role role = repository.findRoleById(id);
        entityManager.refresh(role);
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
    @Transactional
    public void editDepartment(Long departmentId, Long roleId) {
        Department departmentEntity = departmentRepository.findDepartmentById(departmentId);
        entityManager.refresh(departmentEntity);
        Role roleEntity = repository.findRoleById(roleId);
        entityManager.refresh(roleEntity);
        roleEntity.setDepartment(departmentEntity);
        repository.save(roleEntity);
    }

    @Override
    public Iterable<RoleDepartmentDTO> findRoleDepartments() {
        var mapper = Mappers.getMapper(RoleMapper.class);
        return mapper.toRoleDepartmentList(repository.findAll());
    }

}
