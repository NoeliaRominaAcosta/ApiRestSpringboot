package com.itrsa.monolith.controller;

import com.itrsa.monolith.dto.SkillDTO;
import com.itrsa.monolith.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/skill")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @GetMapping
    public List<SkillDTO> listSkills(){
        return skillService.listSkill();
    }

    @PostMapping("/create")
    public void createSkill(@RequestBody SkillDTO skill){
        skillService.createSkill(skill);
    }

    @PutMapping("/edit")
    public void editSkill(@RequestBody SkillDTO skill){
        skillService.editSkill(skill);
    }

    @DeleteMapping("/delete/{skillCode}")
    public void deleteSkill(@PathVariable String skillCode){
        skillService.deleteSkill(skillCode);
    }
}
