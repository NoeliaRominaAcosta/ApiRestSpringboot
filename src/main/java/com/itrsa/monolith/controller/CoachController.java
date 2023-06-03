package com.itrsa.monolith.controller;


import com.itrsa.monolith.dto.CoachDTO;
import com.itrsa.monolith.dto.EmployeeDTO;
import com.itrsa.monolith.entity.Coach;
import com.itrsa.monolith.service.CoachService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/coach")
public class CoachController {

    private CoachService coachService;

    public CoachController (CoachService coachService){
        this.coachService = coachService;
    }

    @Operation(summary = "Get all coaches")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Caoch obtenidos",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Coach.class)) })})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<CoachDTO> getAllCoaches() {
        return coachService.findAll();
    }

    @Operation(summary = "Get a coach by dni")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coach obtenido",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Coach.class)) })})
    @GetMapping("/get/{dni}")
    @ResponseStatus(HttpStatus.OK)
    public CoachDTO getCoachByDni(@PathVariable String dni) {
        return coachService.findByDni(dni);
    }


    @Operation(summary = "Save coach")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coach saved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Coach.class)) })})
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public void createCoach(@RequestBody CoachDTO coach) {
        coachService.save(coach);
    }

    @Operation(summary = "Update coach")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coach updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Coach.class)) })})
    @PutMapping("/update/{dni}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CoachDTO> updateCoach(@PathVariable String dni, @RequestBody CoachDTO input){

        CoachDTO coach = coachService.findByDni(dni);

        if(coach == null){
            return ResponseEntity.notFound().build();
        }

        coachService.update(input);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete coach")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coach deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Coach.class)) })})
    @DeleteMapping("/delete/{dni}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCoach(@PathVariable String dni){
        coachService.deleteByDni(dni);
    }


    @Operation(summary = "Get all employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee obtenidos",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Coach.class)) })})
    @GetMapping("/listEmployee/{dniCoach}")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<EmployeeDTO> getAllEmployee(@PathVariable String dniCoach) {
        return coachService.listEmployees(dniCoach);
    }



}
