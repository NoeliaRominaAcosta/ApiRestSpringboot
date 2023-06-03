package com.itrsa.monolith.mapper;

import com.itrsa.monolith.entity.Employee;
import com.itrsa.monolith.dto.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper
public interface EmployeeMapper {
    @Mapping(target="name", source="entity.name")
    @Mapping(target="lastName", source="entity.lastName")
    @Mapping(target="dni", source="entity.dni")
    @Mapping(target="longGoal", source="entity.longGoal")
    @Mapping(target="shortGoal", source="entity.shortGoal")
    EmployeeDTO toEmployeeDTO(Employee entity);

    @Mapping(target="name", source="dto.name")
    @Mapping(target="lastName", source="dto.lastName")
    @Mapping(target="dni", source="dto.dni")
    @Mapping(target="longGoal", source="dto.longGoal")
    @Mapping(target="shortGoal", source="dto.shortGoal")
    @Mapping(target = "id", ignore = true)
    Employee toEmployee(EmployeeDTO dto);

    Iterable<EmployeeDTO> toList(Iterable<Employee> employees);
}
