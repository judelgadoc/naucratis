package com.naucratis.naucratis.repository;

import com.naucratis.naucratis.model.library.Library;
import org.springframework.data.repository.CrudRepository;

public interface LibraryRepository extends CrudRepository<Library, Long>
{
    Library findByName(String name);
}

