package com.itrsa.monolith.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itrsa.monolith.dto.ErrorHTTPDTO;
import com.itrsa.monolith.entity.Product;
import com.itrsa.monolith.entity.User;
import com.itrsa.monolith.exception.RandomException;
import com.itrsa.monolith.service.UserServiceBusiness;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/user/random/exception")
public class UserExceptionController {

    private UserServiceBusiness userServiceBusiness;

    public UserExceptionController(UserServiceBusiness userServiceBusiness) {
        this.userServiceBusiness = userServiceBusiness;
    }



    @Operation(summary = "Obtiene un usuario o una excepcion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario obtenido",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "404", description = "Excepcion al no encontrar el usuario",
                    content = { @Content(mediaType = "",
                            schema = @Schema(implementation = ErrorHTTPDTO.class))})})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public User getUserRandomException() throws RandomException, IOException {

        return userServiceBusiness.getUserRandomException().body();

    }
}
