package com.itrsa.monolith.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.itrsa.monolith.dto.OpportunityDTO;
import com.itrsa.monolith.entity.Opportunity;

@Mapper
public interface OpportunityMapper {
    @Mapping(target="opportunityName", source="entity.opportunityName")
    @Mapping(target="description", source="entity.description")
    @Mapping(target="code", source="entity.code")
    OpportunityDTO toOpportunityDTO(Opportunity entity);

    @Mapping(target="opportunityName", source="dto.opportunityName")
    @Mapping(target="description", source="dto.description")
    @Mapping(target="code", source="dto.code")
    @Mapping(target = "id", ignore = true)
    Opportunity toOpportunity(OpportunityDTO dto);

    Iterable<OpportunityDTO> toList(Iterable<Opportunity> opportunities);
}
