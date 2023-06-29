package com.itrsa.monolith.controller;

import com.itrsa.monolith.dto.AssignerDTO;
import com.itrsa.monolith.entity.Assigner;
import com.itrsa.monolith.service.AssignerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/assigner")
public class AssignerController {
    @Autowired
    private AssignerService serviceAssigner;

    @Operation(summary = "Get all assigner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " Assigners obtained",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Assigner.class)) })})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<AssignerDTO> getAllAssigner() {
        return serviceAssigner.findAll();
    }

    @Operation(summary = "Get an assigner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assigned obtained",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Assigner.class)) })})
    @GetMapping("/get/{dni}")
    public AssignerDTO getAssigner(@PathVariable String dni) {
        return  serviceAssigner.getAssigner(dni);
    }

    @Operation(summary = "Update Assigner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assigner updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Assigner.class)) })})
    @PutMapping("/edit")
    public void editAssigner(@RequestBody AssignerDTO assigner) {
        serviceAssigner.editAssigner(assigner);
    }

    @Operation(summary = "Delete assigner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assigner deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Assigner.class)) })})
    @DeleteMapping("/delete/{dni}")
    public void deleteAssigner(@PathVariable String dni) {
        serviceAssigner.deleteAssigner(dni);
    }

    @Operation(summary = "Save assigner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assigner saved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Assigner.class)) })})
    @PostMapping("/create")
    public void addAssigner(@RequestBody AssignerDTO assigner) {
        serviceAssigner.addAssigner(assigner);
    }
}
