package com.naucratis.naucratis.repository;

import com.naucratis.naucratis.model.library.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, String>
{

}
