package com.itrsa.monolith.service;

import com.itrsa.monolith.dto.EmployeeShorterDTO;
import com.itrsa.monolith.dto.ResourceDTO;
import com.itrsa.monolith.entity.*;
import com.itrsa.monolith.mapper.ResourceMapper;
import com.itrsa.monolith.repository.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.itrsa.monolith.mapper.EmployeeMapper;
import com.itrsa.monolith.dto.EmployeeDTO;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;


@Component
public class EmployeeServiceBusiness implements EmployeeService {
    private EmployeeRepository repository;
    public EmployeeServiceBusiness(EmployeeRepository repository) {
        this.repository = repository;
    }
    @Autowired
    private SkillRepository skillRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private CoachRepository coachRepo;

    @Autowired
    private ResourceRepository resourceRepo;

    @Autowired
    private OpportunityRepository opportunityRepo;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Iterable<EmployeeDTO> findAll() {
        var mapper = Mappers.getMapper(EmployeeMapper.class);
        return mapper.toList(repository.findAll());
    }

    @Override
    public void save(EmployeeDTO dto, String dniCoach) {
        var mapper = Mappers.getMapper(EmployeeMapper.class);
        Employee employee = mapper.toEmployee(dto);
        Coach coach = coachRepo.findByDni(dniCoach);
        employee.setCoach(coach);
        repository.save(employee);
    }

    @Override
    public EmployeeDTO findByDni(String dni) {
        var mapper = Mappers.getMapper(EmployeeMapper.class);
        return mapper.toEmployeeDTO(repository.findByDni(dni));
    }

    @Override
    @Transactional
    public void edit(EmployeeDTO dto) {
        Employee employee = repository.findByDni(dto.getDni());
        entityManager.refresh(employee);
        employee.setName(dto.getName());
        employee.setLastName(dto.getLastName());
        employee.setShortGoal(dto.getShortGoal());
        employee.setLongGoal(dto.getLongGoal());
        repository.save(employee);
    }

    @Override
    public void deleteByDni(String dni) {
        Employee employee = repository.findByDni(dni);
        repository.delete(employee);
    }

    @Override
    public void addSkill(Long skillId, Long employeeId) {
        Skill skillEntity = skillRepo.findById(skillId).orElse(null);
        Optional<Employee> employeeOptional = repository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Employee employeeEntity = employeeOptional.get();
            if (skillEntity != null) {
                employeeEntity.getSkillList().add(skillEntity);
                repository.save(employeeEntity);
            }
        }
    }

    @Override
    public void addRole(Long roleId, String employeeDni) {
        Role roleEntity = roleRepo.findById(roleId).orElse(null);
        Employee employeeEntity = repository.findByDni(employeeDni);

        employeeEntity.getRoleList().add(roleEntity);

        repository.save(employeeEntity);

    }

    @Override
    public void addResource(String employeeDni, Long resourceId) {
        Resource resource = resourceRepo.findById(resourceId).orElse(null);
        Employee employee = repository.findByDni(employeeDni);
        employee.getResourceList().add(resource);
        repository.save(employee);
    }

    @Override
    public void addOpportunity(String employeeDni, Long opportunityId) {
        Opportunity opportunity = opportunityRepo.findById(opportunityId).orElse(null);
        Employee employee = repository.findByDni(employeeDni);
        employee.getOpportunityList().add(opportunity);
        repository.save(employee);

    }

    @Override
    public Iterable<ResourceDTO> listResources(String dni) {
        Employee employee = repository.findByDni(dni);
        var mapper = Mappers.getMapper(ResourceMapper.class);

        return mapper.toList(employee.getResourceList());
    }

    @Override
    public Iterable<EmployeeShorterDTO> listEmployeesBySkill(String skillCode) {
        Skill skillEntity = skillRepo.findBySkillCode(skillCode);
        var mapper = Mappers.getMapper(EmployeeMapper.class);

        return mapper.toListEmployeeShorter(skillEntity.getEmployeeList());
    }

}
