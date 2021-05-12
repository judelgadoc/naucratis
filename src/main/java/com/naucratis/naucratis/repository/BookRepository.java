package com.naucratis.naucratis.repository;

import com.naucratis.naucratis.model.library.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long>
{

}
