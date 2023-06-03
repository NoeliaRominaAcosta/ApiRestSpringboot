package com.itrsa.monolith.controller;
import com.itrsa.monolith.dto.EmployeeDTO;
import com.itrsa.monolith.dto.EmployeeResourceGoalsDTO;
import com.itrsa.monolith.entity.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.itrsa.monolith.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private EmployeeService service;
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @Operation(summary = "Gets all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees obtained",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) })})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<EmployeeDTO> getAllEmployees() {
        return service.findAll();
    }

    @Operation(summary = "Save an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) })})
    @PostMapping("/{coachDni}")
    @ResponseStatus(HttpStatus.OK)
    public void createEmployee(@RequestBody EmployeeDTO employee, @PathVariable String coachDni) {
        service.save(employee, coachDni);
    }

    @Operation(summary = "Add an skill to employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added skill",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) })})
    @PostMapping("/addSkill/{employeeId}/{skillId}")
    @ResponseStatus(HttpStatus.OK)
    public void addSkill(@PathVariable Long employeeId,@PathVariable Long skillId) {
        service.addSkill(skillId,employeeId);
    }

    @Operation(summary = "Save an role in employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role saved in employee",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) })})

    @PostMapping("/addRole/{employeeDni}/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public void addRole(@PathVariable String employeeDni,@PathVariable Long roleId) {
        service.addRole(roleId,employeeDni);
    }

    @Operation(summary = "Get employed by DNI")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee obtained by DNI",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) })})
    @GetMapping("/{dni}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO getEmployeeByDni(@PathVariable String dni) {
        return service.findByDni(dni);
    }

    @Operation(summary = "Edit an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee edited",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) })})
    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public void editEmployee(@RequestBody EmployeeDTO employee) {service.edit(employee); }

    @Operation(summary = "Delete an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted employee",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) })})

    @DeleteMapping("/delete/{dni}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable String dni) {service.deleteByDni(dni); }


    @Operation(summary = "Get resources and goals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resources and Goals obtained by DNI",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) })})
    @GetMapping("/resourcesgoals/{dni}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResourceGoalsDTO getEmployeeResourceGoal(@PathVariable String dni) {
        EmployeeResourceGoalsDTO employeeresource = new EmployeeResourceGoalsDTO();

        EmployeeDTO employeedto = service.findByDni(dni);

        employeeresource.setShortGoal(employeedto.getShortGoal());
        employeeresource.setLongGoal(employeedto.getLongGoal());
        employeeresource.setResources(service.listResources(dni));

        return employeeresource;
    }
}
