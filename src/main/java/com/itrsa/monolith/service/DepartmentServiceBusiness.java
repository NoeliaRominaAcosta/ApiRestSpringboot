package com.itrsa.monolith.service;

import com.itrsa.monolith.dto.RoleDTO;
import com.itrsa.monolith.mapper.DepartmentMapper;
import com.itrsa.monolith.dto.DepartmentDTO;
import com.itrsa.monolith.entity.Department;
import com.itrsa.monolith.mapper.RoleMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.itrsa.monolith.repository.DepartmentRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
public class DepartmentServiceBusiness implements DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private EntityManager entityManager;
    public DepartmentServiceBusiness(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<DepartmentDTO> findAll() {
        var mapper = Mappers.getMapper(DepartmentMapper.class);
        return mapper.toList(repository.findAll());
    }

    @Override
    public void save(DepartmentDTO dto) {
        var mapper = Mappers.getMapper(DepartmentMapper.class);
        repository.save(mapper.toDepartment(dto));
    }
    @Override
    public DepartmentDTO findDepartmentById(Long id) {
        var mapper = Mappers.getMapper(DepartmentMapper.class);
        return mapper.toDepartmentDTO(repository.findDepartmentById(id));
    }
    @Override
    @Transactional
    public void edit(DepartmentDTO dto, Long id) {
        Department department = repository.findDepartmentById(id);
        entityManager.refresh(department);
        department.setName(dto.getName());
        repository.save(department);
    }
    @Override
    public void deleteDepartmentById(Long id) {
        Department department = repository.findDepartmentById(id);
        repository.delete(department);
    }

    @Override
    public Iterable<RoleDTO> listRoles(Long id) {
        Department department = repository.findDepartmentById(id);
        var mapper = Mappers.getMapper(RoleMapper.class);

        return mapper.toList(department.getRoleList());
    }
}
