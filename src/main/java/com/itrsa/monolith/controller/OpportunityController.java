package com.itrsa.monolith.controller;

import com.itrsa.monolith.dto.OpportunityDTO;
import com.itrsa.monolith.requestobjects.OpportunitySkillReq;
import com.itrsa.monolith.service.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opportunity")
public class OpportunityController {

    @Autowired
    private OpportunityService serviceOpportunity;

    @GetMapping("/get/{code}")
    public OpportunityDTO getOpportunity(@PathVariable String code) {
        return  serviceOpportunity.getOpportunity(code);
    }

    @GetMapping
    public List<OpportunityDTO> listOpportunities() {
        return serviceOpportunity.listOpportunities();
    }

    @PutMapping("/edit")
    public void editOpportunity(@RequestBody OpportunityDTO opportunity) {
        serviceOpportunity.editOpportunity(opportunity);
    }

    @PostMapping("/create")
    public void createOpportunity(@RequestBody OpportunitySkillReq request) {
        OpportunityDTO opportunity = request.getOpportunity();
        Iterable<String> skillCodes = request.getSkillCodes();
        serviceOpportunity.createOpportunity(opportunity, skillCodes);
    }

    @DeleteMapping("/delete/{code}")
    public void deleteOpportunity(@PathVariable String code) {
        serviceOpportunity.deleteOpportunity(code);
    }
}

