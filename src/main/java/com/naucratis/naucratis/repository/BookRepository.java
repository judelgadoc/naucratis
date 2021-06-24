package com.naucratis.naucratis.repository;

import com.naucratis.naucratis.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {

}
