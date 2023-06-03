package com.itrsa.monolith.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.itrsa.monolith.dto.SkillDTO;
import com.itrsa.monolith.entity.Skill;

@Mapper
public interface SkillMapper {
        @Mapping(target="name", source="entity.name")
        @Mapping(target="skillCode", source="entity.skillCode")
        @Mapping(target="seniority", source="entity.seniority")
        SkillDTO toSkillDTO(Skill entity);

        @Mapping(target="name", source="dto.name")
        @Mapping(target="skillCode", source="dto.skillCode")
        @Mapping(target="seniority", source="dto.seniority")
        @Mapping(target = "id", ignore = true)
        Skill toSkill(SkillDTO dto);
        Iterable<SkillDTO> toList(Iterable<Skill> skills);
}