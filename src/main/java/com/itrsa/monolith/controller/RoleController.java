package com.itrsa.monolith.controller;
import com.itrsa.monolith.dto.RoleDTO;
import com.itrsa.monolith.dto.RoleDepartmentDTO;
import com.itrsa.monolith.entity.Employee;
import com.itrsa.monolith.entity.Role;
import com.itrsa.monolith.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/role")
public class RoleController {

    private RoleService service;
    public RoleController(RoleService service) {
        this.service = service;
    }

    @Operation(summary = "Get all roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles obtained",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) })})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<RoleDTO> getAllRoles() {
        return service.findAll();
    }

    @Operation(summary = "Gets all roles with departments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles obtained",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) })})
    @GetMapping("/department")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<RoleDepartmentDTO> getAllRolesDepartment() {
        return service.findRoleDepartments();
    }

    @Operation(summary = "Save a role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) })})
    @PostMapping("/{departmentId}")
    @ResponseStatus(HttpStatus.OK)
    public void createRole(@RequestBody RoleDTO role, @PathVariable Long departmentId) {
        service.save(role, departmentId);
    }

    @Operation(summary = "Get role by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role obtained by id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) })})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleDTO getRoleById(@PathVariable Long id) {
        return service.findRoleById(id);
    }

    @Operation(summary = "Update a role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) })})
    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editRole(@RequestBody RoleDTO role, @PathVariable Long id) {service.edit(role, id); }

    @Operation(summary = "Delete a role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) })})
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRole(@PathVariable Long id) {service.deleteById(id); }

    @Operation(summary = "Edit role department")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated role department",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Role.class)) })})
    @PutMapping("/editDepartment/{departmentId}/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public void editDepartment(@PathVariable Long departmentId, @PathVariable Long roleId){
        service.editDepartment(departmentId,roleId);
    }
}
