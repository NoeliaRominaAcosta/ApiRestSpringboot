package com.itrsa.monolith.service;

import com.itrsa.monolith.mapper.AssignerMapper;
import com.itrsa.monolith.dto.AssignerDTO;
import com.itrsa.monolith.entity.Assigner;
import com.itrsa.monolith.repository.AssignerRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssignerServiceBusiness implements AssignerService {
    @Autowired
    private AssignerRepository assignerRepo;

    @Override
    public AssignerDTO getAssigner(String dni) {
        var mapper = Mappers.getMapper(AssignerMapper.class);
        return mapper.toAssignerDTO(assignerRepo.findBydni(dni));
    }

    @Override
    public void editAssigner(AssignerDTO assigner) {
        String dni = assigner.getDni();


        Assigner assignerEntity = assignerRepo.findBydni(dni);
        assignerEntity.setName(assigner.getName());
        assignerEntity.setLastname(assigner.getLastname());
        assignerEntity.setShortGoal(assigner.getShortGoal());
        assignerEntity.setLongGoal(assigner.getLongGoal());

        assignerRepo.save(assignerEntity);
    }

    @Override
    public void deleteAssigner(String dni) {
        Assigner entity = assignerRepo.findBydni(dni);
        assignerRepo.delete(entity);
    }

    @Override
    public void addAssigner(AssignerDTO assigner) {
        var mapper = Mappers.getMapper(AssignerMapper.class);
        assignerRepo.save(mapper.toAssigner(assigner));
    }
}
