package com.itrsa.monolith.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itrsa.monolith.dto.BookDTO;
import com.itrsa.monolith.entity.Book;
import com.itrsa.monolith.entity.Product;
import com.itrsa.monolith.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private BookService service;

    public BookingController(BookService service) {
        this.service = service;
    }

    @Operation(summary = "Obtiene todos los libros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libros obtenidos",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) })})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<BookDTO> getAllBooks() {
        return service.findAll();
    }

    @Operation(summary = "Guardar un libro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro creado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) })})
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createBook(@RequestBody BookDTO book) {
        service.save(book);
    }
}
