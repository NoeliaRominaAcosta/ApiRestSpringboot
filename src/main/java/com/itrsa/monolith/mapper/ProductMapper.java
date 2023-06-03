package com.itrsa.monolith.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.itrsa.monolith.dto.ProductDTO;
import com.itrsa.monolith.entity.Product;

@Mapper
public interface ProductMapper {

    @Mapping(target="name", source="entity.name")
    @Mapping(target="description", source="entity.description")
    ProductDTO toDTO(Product entity);

    @Mapping(target="name", source="dto.name")
    @Mapping(target="description", source="dto.description")
    @Mapping(target="price", source="dto.price")
    @Mapping(target = "id", ignore = true)
    Product toProduct(ProductDTO dto);

	Iterable<ProductDTO> toIterable(Iterable<Product> products);
    
}
