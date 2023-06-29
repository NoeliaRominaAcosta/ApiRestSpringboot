package com.itrsa.monolith.controller;


import com.itrsa.monolith.dto.ResourceDTO;
import com.itrsa.monolith.entity.Resource;
import com.itrsa.monolith.service.ResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/resource")
public class ResourceController {

    private ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {

        this.resourceService = resourceService;
    }

    @Operation(summary = "Get all resources")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resources obtained",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Resource.class)) })})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<ResourceDTO> getAllResources() {
        return resourceService.findAll();
    }

    @Operation(summary = "Get a resource by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource obtained",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Resource.class)) })})
    @GetMapping("/get/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResourceDTO getResourceByName(@PathVariable String name) {
        return resourceService.findByName(name);
    }

    @Operation(summary = "Save resource")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource saved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Resource.class)) })})
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public void createResource(@RequestBody ResourceDTO resource) {
        resourceService.save(resource);
    }

    @Operation(summary = "Update resource")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Resource.class)) })})
    @PutMapping("/update/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResourceDTO> updateResource(@PathVariable String name, @RequestBody ResourceDTO input){

        ResourceDTO resource = resourceService.findByName(name);

        if(resource == null){
            return ResponseEntity.notFound().build();
        }

        resourceService.update(input);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete resource")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Resource deleted successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Resource.class)) })})
    @DeleteMapping("/delete/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteResource(@PathVariable String name){
        resourceService.deleteByName(name);
    }


}
