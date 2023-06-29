package com.itrsa.monolith.service;

import com.itrsa.monolith.mapper.ResourceMapper;
import com.itrsa.monolith.dto.ResourceDTO;
import com.itrsa.monolith.entity.Resource;
import com.itrsa.monolith.repository.ResourceRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component

public class ResourceServiceBusiness implements ResourceService{
    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    public ResourceServiceBusiness(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Autowired
    EntityManager entityManager;

    @Override
    public Iterable<ResourceDTO> findAll() {
        var mapper = Mappers.getMapper(ResourceMapper.class);
        return mapper.toList(resourceRepository.findAll());
    }
    @Override
    public ResourceDTO findByName(String name) {
        var mapper = Mappers.getMapper(ResourceMapper.class);
        return mapper.toResourceDTO(resourceRepository.findByName(name));
    }
    @Override
    public void save(ResourceDTO dto) {
        var mapper = Mappers.getMapper(ResourceMapper.class);
        resourceRepository.save(mapper.toResource(dto));
    }

    @Override
    @Transactional
    public void update(ResourceDTO dto) {
       String name = dto.getName();
       Resource entity = resourceRepository.findByName(name);
       entityManager.refresh(entity);
       entity.setName(dto.getName());
       entity.setDescription(dto.getDescription());
       entity.setUrl(dto.getUrl());

       resourceRepository.save(entity);

    }

    @Override
    public void deleteByName(String name) {
        Resource resource = resourceRepository.findByName(name);
        resourceRepository.delete(resource);
    }

}

