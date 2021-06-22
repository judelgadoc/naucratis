package com.naucratis.naucratis.service;

import com.naucratis.naucratis.model.library.CopyBook;
import com.naucratis.naucratis.repository.CopyBookRepository;
import org.springframework.stereotype.Service;

@Service
public class CopyBookService
{
    private final CopyBookRepository copyBookRepository;

    public CopyBookService(CopyBookRepository copyBookRepository) {
        this.copyBookRepository = copyBookRepository;
    }

    public CopyBook save(CopyBook copyBook)
    {
        return copyBookRepository.save(copyBook);
    }

    public CopyBook findById(long id)
    {
        return copyBookRepository.findById(id).get();
    }

    public void delete(long id)
    {
        copyBookRepository.deleteById(id);
    }
}
