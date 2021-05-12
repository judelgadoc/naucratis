package com.naucratis.naucratis.service;

import com.naucratis.naucratis.model.library.Author;
import com.naucratis.naucratis.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService
{
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository)
    {
        this.authorRepository = authorRepository;
    }

    public boolean existsByName(String name)
    {
        return authorRepository.existsById(name);
    }

    public void save(Author author)
    {
        authorRepository.save(author);
    }
}
