package com.itrsa.monolith.controller;

import com.itrsa.monolith.dto.SkillDTO;
import com.itrsa.monolith.entity.Skill;
import com.itrsa.monolith.service.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/skill")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @Operation(summary = "Get all skills")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Skills obtained",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Skill.class)) })})
    @GetMapping
    public List<SkillDTO> listSkills(){
        return skillService.listSkill();
    }

    @Operation(summary = "Create a skills")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Skill created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Skill.class)) })})
    @PostMapping("/create")
    public void createSkill(@RequestBody SkillDTO skill){
        skillService.createSkill(skill);
    }

    @Operation(summary = "Update a skill")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Skill updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Skill.class)) })})
    @PutMapping("/edit")
    public void editSkill(@RequestBody SkillDTO skill){
        skillService.editSkill(skill);
    }

    @Operation(summary = "Delete a skill")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Skill deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Skill.class)) })})
    @DeleteMapping("/delete/{skillCode}")
    public void deleteSkill(@PathVariable String skillCode){
        skillService.deleteSkill(skillCode);
    }
}
