package com.itrsa.monolith.service;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.itrsa.monolith.mapper.BookMapper;
import com.itrsa.monolith.dto.BookDTO;
import com.itrsa.monolith.repository.BookingRepository;

@Component
public class BookServiceBusiness implements BookService {
    private BookingRepository repository;

    public BookServiceBusiness(BookingRepository repository) {
        this.repository = repository;
    }

	@Override
	public Iterable<BookDTO> findAll() {
		var mapper = Mappers.getMapper(BookMapper.class);
		return mapper.toList(repository.findAll());
	}

	@Override
	public void save(BookDTO dto) {
		var mapper = Mappers.getMapper(BookMapper.class);
		repository.save(mapper.toBook(dto));		
	}
    

}
