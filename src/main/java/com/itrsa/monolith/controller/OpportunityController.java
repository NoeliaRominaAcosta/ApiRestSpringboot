package com.itrsa.monolith.controller;

import com.itrsa.monolith.dto.EmployeeShorterDTO;
import com.itrsa.monolith.dto.OpportunityDTO;
import com.itrsa.monolith.entity.Opportunity;
import com.itrsa.monolith.requestobjects.OpportunitySkillReq;
import com.itrsa.monolith.service.OpportunityService;
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
@RequestMapping("/api/opportunity")
public class OpportunityController {

    @Autowired
    private OpportunityService serviceOpportunity;

    @Operation(summary = "Get an opportunity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Opportunity obtained",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Opportunity.class)) })})
    @GetMapping("/get/{code}")
    public OpportunityDTO getOpportunity(@PathVariable String code) {
        return  serviceOpportunity.getOpportunity(code);
    }

    @Operation(summary = "Get all opportunities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Opportunities obtained",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Opportunity.class)) })})
    @GetMapping
    public List<OpportunityDTO> listOpportunities() {
        return serviceOpportunity.listOpportunities();
    }

    @Operation(summary = "Update an opportunity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Opportunity updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Opportunity.class)) })})
    @PutMapping("/edit")
    public void editOpportunity(@RequestBody OpportunityDTO opportunity) {
        serviceOpportunity.editOpportunity(opportunity);
    }

    @Operation(summary = "Create a opportunity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Opportunity created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Opportunity.class)) })})
    @PostMapping("/create")
    public void createOpportunity(@RequestBody OpportunitySkillReq request) {
        OpportunityDTO opportunity = request.getOpportunity();
        Iterable<String> skillCodes = request.getSkillCodes();
        serviceOpportunity.createOpportunity(opportunity, skillCodes);
    }

    @Operation(summary = "Delete a opportunity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Opportunity deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Opportunity.class)) })})
    @DeleteMapping("/delete/{code}")
    public void deleteOpportunity(@PathVariable String code) {
        serviceOpportunity.deleteOpportunity(code);
    }

    @Operation(summary = "Get employees for opportunities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Opportunities for employees obtained",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Opportunity.class)) })})
    @GetMapping("/applicants/{code}")
    public List<EmployeeShorterDTO> getApplicants(@PathVariable String code){
        return serviceOpportunity.listPostulants(code);
    }
}

