package com.itrsa.monolith.controller;

import com.itrsa.monolith.dto.DepartmentDTO;
import com.itrsa.monolith.dto.RoleDTO;
import com.itrsa.monolith.entity.*;
import com.itrsa.monolith.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    private DepartmentService service;
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @Operation(summary = "Get all departments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Departments obtained",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Department.class)) })})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<DepartmentDTO> getAllDepartments() {
        return service.findAll();
    }

    @Operation(summary = "Save a department")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Department created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Department.class)) })})
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createDepartment(@RequestBody DepartmentDTO department) {
        service.save(department);
    }

    @Operation(summary = "Get department by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Department obtained",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Department.class)) })})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentDTO getDepartmentById(@PathVariable Long id) {
        return service.findDepartmentById(id);
    }

    @Operation(summary = "Edit a department")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Department edited",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Department.class)) })})
    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editDepartment(@RequestBody DepartmentDTO department, @PathVariable Long id) {service.edit(department, id); }

    @Operation(summary = "Delete a department by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Department deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Department.class)) })})

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDepartment(@PathVariable Long id) {service.deleteDepartmentById(id); }

    @Operation(summary = "Get all roles by department")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles obtained by department",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Department.class)) })})
    @GetMapping("/listRoles/{departmentId}")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<RoleDTO> getAllRoles(@PathVariable Long departmentId) {
        return service.listRoles(departmentId);
    }
}
