package com.itrsa.monolith.mapper;
import com.itrsa.monolith.dto.AssignerDTO;
import com.itrsa.monolith.entity.Assigner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AssignerMapper {
    @Mapping(target="name", source="entity.name")
    @Mapping(target="lastname", source="entity.lastname")
    @Mapping(target="dni", source="entity.dni")
    //@Mapping(target="role", source="entity.role")
    @Mapping(target="shortGoal", source="entity.shortGoal")
    @Mapping(target="longGoal", source="entity.longGoal")
    AssignerDTO toAssignerDTO(Assigner entity);

    @Mapping(target="name", source="dto.name")
    @Mapping(target="lastname", source="dto.lastname")
    @Mapping(target="dni", source="dto.dni")
    //@Mapping(target="role", source="dto.role")
    @Mapping(target="shortGoal", source="dto.shortGoal")
    @Mapping(target="longGoal", source="dto.longGoal")
    @Mapping(target = "id", ignore = true)
    Assigner toAssigner(AssignerDTO dto);

    Iterable<AssignerDTO> toList(Iterable<Assigner> assigners);
}
