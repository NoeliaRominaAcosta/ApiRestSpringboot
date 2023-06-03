package com.itrsa.monolith.service;

import com.itrsa.monolith.dto.ResourceDTO;

public interface ResourceService {
    Iterable<ResourceDTO> findAll();

    void save(ResourceDTO dto);

    void update(ResourceDTO dto);

    void deleteByName(String name);

    ResourceDTO findByName(String name);
}
