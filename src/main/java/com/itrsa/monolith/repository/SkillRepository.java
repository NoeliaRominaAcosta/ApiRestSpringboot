package com.itrsa.monolith.repository;

import com.itrsa.monolith.entity.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SkillRepository extends CrudRepository<Skill, Long>{
    Skill findByName(String name);
    Skill findBySkillCode(String skillCode);

}