package com.itrsa.monolith.service;

import com.itrsa.monolith.dto.EmployeeShorterDTO;
import com.itrsa.monolith.mapper.EmployeeMapper;
import com.itrsa.monolith.mapper.OpportunityMapper;
import com.itrsa.monolith.dto.OpportunityDTO;
import com.itrsa.monolith.entity.Opportunity;
import com.itrsa.monolith.entity.Skill;
import com.itrsa.monolith.repository.OpportunityRepository;
import com.itrsa.monolith.repository.SkillRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
public class OpportunityServiceBusiness implements OpportunityService{
    @Autowired
    private OpportunityRepository opportunityRepo;

    @Autowired
    private SkillRepository skillRepo;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<OpportunityDTO> listOpportunities() {
        var mapper = Mappers.getMapper(OpportunityMapper.class);
        return (List<OpportunityDTO>) mapper.toList(opportunityRepo.findAll());
    }

    @Override
    @Transactional
    public void editOpportunity(OpportunityDTO opportunity) {
        String code = opportunity.getCode();
        Opportunity opportunityEntity = opportunityRepo.findByCode(code);
        entityManager.refresh(opportunityEntity);
        opportunityEntity.setOpportunityName(opportunity.getOpportunityName());
        opportunityEntity.setDescription(opportunity.getDescription());

        opportunityRepo.save(opportunityEntity);
    }

    @Override
    public void createOpportunity(OpportunityDTO opportunity , Iterable<String> skillsCode) {
        var mapper = Mappers.getMapper(OpportunityMapper.class);
        ArrayList<Skill> skillsEntity =  new ArrayList<>();
        skillsCode.forEach(code -> skillsEntity.add(skillRepo.findBySkillCode(code)));
        Opportunity entity = mapper.toOpportunity(opportunity);
        entity.setSkillListOpportunity(skillsEntity);
        opportunityRepo.save(entity);
    }

    @Override
    public OpportunityDTO getOpportunity(String code) {
        var mapper = Mappers.getMapper(OpportunityMapper.class);
        return mapper.toOpportunityDTO(opportunityRepo.findByCode(code));
    }

    @Override
    public void deleteOpportunity(String code){
        Opportunity entity = opportunityRepo.findByCode(code);
        opportunityRepo.delete(entity);
    }
    @Override
    public List<EmployeeShorterDTO> listPostulants(String code){
        Opportunity entity = opportunityRepo.findByCode(code);
        var mapper = Mappers.getMapper(EmployeeMapper.class);
        List<EmployeeShorterDTO> dtoList = new ArrayList<>();
        for (var employee: entity.getEmployeeList()){
            EmployeeShorterDTO dto = mapper.toEmployeeOpportunityDTO(employee);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
