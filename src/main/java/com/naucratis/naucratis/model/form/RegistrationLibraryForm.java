package com.naucratis.naucratis.model.form;

import com.naucratis.naucratis.model.library.Library;
import lombok.Data;


@Data
public class RegistrationLibraryForm
{
    private String name;

    public Library toLibrary()
    {
        Library library = new Library(name);

        return library;
    }
}
