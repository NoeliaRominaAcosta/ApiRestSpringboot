package com.itrsa.monolith.mapper;
import com.itrsa.monolith.dto.DepartmentDTO;
import com.itrsa.monolith.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper

public interface DepartmentMapper {
    @Mapping(target="name", source="entity.name")
    DepartmentDTO toDepartmentDTO(Department entity);

    @Mapping(target="name", source="dto.name")
    @Mapping(target = "id", ignore = true)
    Department toDepartment(DepartmentDTO dto);

    Iterable<DepartmentDTO> toList(Iterable<Department> departments);
}
