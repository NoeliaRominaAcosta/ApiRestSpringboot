package com.itrsa.monolith.mapper;

import com.itrsa.monolith.dto.ResourceDTO;
import com.itrsa.monolith.entity.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ResourceMapper {

    @Mapping(target="name", source="entity.name")
    @Mapping(target="description", source="entity.description")
    @Mapping(target = "url", source = "entity.url")
    ResourceDTO toResourceDTO(Resource entity);

    @Mapping(target="name", source="dto.name")
    @Mapping(target="description", source="dto.description")
    @Mapping(target = "url", source = "dto.url")
    @Mapping(target = "id", ignore = true)
    Resource toResource(ResourceDTO dto);

    Iterable<ResourceDTO> toList(Iterable<Resource> resource);
}
