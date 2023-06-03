package com.itrsa.monolith.service;

import com.itrsa.monolith.dto.SkillDTO;

import java.util.List;

public interface SkillService {
    List<SkillDTO> listSkill();
    void editSkill(SkillDTO skill);
    void createSkill(SkillDTO skill);
    void deleteSkill(String skillCode);
}
