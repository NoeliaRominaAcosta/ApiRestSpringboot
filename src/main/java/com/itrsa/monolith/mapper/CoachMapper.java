package com.itrsa.monolith.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.itrsa.monolith.dto.CoachDTO;
import com.itrsa.monolith.entity.Coach;

@Mapper
public interface CoachMapper {

    @Mapping(target="name", source="entity.name")
    @Mapping(target="lastname", source="entity.lastname")
    @Mapping(target = "dni", source = "entity.dni")
    @Mapping(target = "role", source = "entity.role")
    @Mapping(target = "longGoal", source = "entity.longGoal")
    @Mapping(target = "shortGoal", source = "entity.shortGoal")
    CoachDTO toCoachDTO(Coach entity);

    @Mapping(target="name", source="dto.name")
    @Mapping(target="lastname", source="dto.lastname")
    @Mapping(target = "dni", source = "dto.dni")
    @Mapping(target = "role", source = "dto.role")
    @Mapping(target = "longGoal", source = "dto.longGoal")
    @Mapping(target = "shortGoal", source = "dto.shortGoal")
    @Mapping(target = "id", ignore = true)
    Coach toCoach(CoachDTO dto);

    Iterable<CoachDTO> toList(Iterable<Coach> coach);
}
