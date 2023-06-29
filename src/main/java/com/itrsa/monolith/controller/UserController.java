package com.itrsa.monolith.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.itrsa.monolith.dto.UserDTO;
import com.itrsa.monolith.entity.User;
import com.itrsa.monolith.service.UserServiceBusiness;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/user")
public class UserController {
    private UserServiceBusiness userServiceBusiness;

    public UserController(UserServiceBusiness userServiceBusiness) {
        this.userServiceBusiness = userServiceBusiness;
    }

    @Operation(summary = "Obtiene un usuario de Github")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario obtenido",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) })})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser() throws IOException {

        return userServiceBusiness.getUser();

    }


}
