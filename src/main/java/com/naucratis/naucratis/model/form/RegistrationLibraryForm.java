package com.naucratis.naucratis.model.form;

import com.naucratis.naucratis.model.library.Library;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Data
public class RegistrationLibraryForm
{
    private String name;

    private MultipartFile image;


    public Library toLibrary()
    {
        Library library = new Library(name);

        try {
            library.setImage(image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return library;
    }
}
