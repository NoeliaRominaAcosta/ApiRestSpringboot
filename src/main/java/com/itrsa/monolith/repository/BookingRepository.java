package com.itrsa.monolith.repository;


import com.itrsa.monolith.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Book, Long> {

}
