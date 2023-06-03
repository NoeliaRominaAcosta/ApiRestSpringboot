package com.itrsa.monolith.service;

import com.itrsa.monolith.dto.BookDTO;

public interface BookService {

	Iterable<BookDTO> findAll();

	void save(BookDTO dto);
}
