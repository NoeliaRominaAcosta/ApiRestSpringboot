package com.itrsa.monolith.service;

import com.itrsa.monolith.mapper.SkillMapper;
import com.itrsa.monolith.dto.SkillDTO;
import com.itrsa.monolith.entity.Skill;
import com.itrsa.monolith.repository.SkillRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
@Component
public class SkillServiceBusiness implements SkillService {
    @Autowired
    private SkillRepository skillRepo;

    @Autowired
    EntityManager entityManager;

    @Override
    public List<SkillDTO> listSkill() {
        var mapper = Mappers.getMapper(SkillMapper.class);
        return (List<SkillDTO>) mapper.toList(skillRepo.findAll());
    }

    @Override
    @Transactional
    public void editSkill(SkillDTO skill) {
    String name = skill.getName();
    Skill skillEntity = skillRepo.findByName(name);
    entityManager.refresh(skillEntity);
    skillRepo.save(skillEntity);
    }

    @Override
    public void createSkill(SkillDTO skill) {
    var mapper = Mappers.getMapper(SkillMapper.class);
    skillRepo.save(mapper.toSkill(skill));
    }

    @Override
    public void deleteSkill(String skillCode) {
        Skill entity = skillRepo.findBySkillCode(skillCode);
        skillRepo.delete(entity);
    }
}
