package com.itrsa.monolith.controller;


import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itrsa.monolith.mapper.ProductMapper;
import com.itrsa.monolith.dto.ProductDTO;
import com.itrsa.monolith.entity.Product;
import com.itrsa.monolith.repository.ProductRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Operation(summary = "Buscar todos los productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos encontrados.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) })})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<ProductDTO> getAllProducts() {
    	var mapper = Mappers.getMapper(ProductMapper.class);
        return mapper.toIterable(productRepository.findAll());
    }

    @Operation(summary = "Guardar un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto creado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) })})
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createProduct(@RequestBody ProductDTO product) {
    	var mapper = Mappers.getMapper(ProductMapper.class);
        productRepository.save(mapper.toProduct(product));
    }
}
