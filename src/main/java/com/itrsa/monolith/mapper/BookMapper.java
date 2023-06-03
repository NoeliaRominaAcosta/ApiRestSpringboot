package com.itrsa.monolith.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.itrsa.monolith.dto.BookDTO;
import com.itrsa.monolith.entity.Book;


@Mapper
public interface BookMapper {

    @Mapping(target="name", source="entity.name")
    @Mapping(target="description", source="entity.description")
    BookDTO toBookDTO(Book entity);

    @Mapping(target="name", source="dto.name")
    @Mapping(target="description", source="dto.description")
    @Mapping(target = "id", ignore = true)
    Book toBook(BookDTO dto);
    
    Iterable<BookDTO> toList(Iterable<Book> books);
}
