package com.naucratis.naucratis.access;

import org.springframework.data.jpa.repository.JpaRepository;
import com.naucratis.naucratis.model.Book;

public interface BookAccess extends JpaRepository <Book, String>{

}