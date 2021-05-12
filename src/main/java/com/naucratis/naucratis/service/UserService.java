package com.naucratis.naucratis.service;

import com.naucratis.naucratis.model.library.Library;
import com.naucratis.naucratis.model.user.Administrator;
import com.naucratis.naucratis.model.user.User;
import com.naucratis.naucratis.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    private UserRepository userRepository;
    private LibraryService libraryService;

    public UserService(UserRepository userRepository,
                       LibraryService libraryService)
    {
        this.userRepository = userRepository;
        this.libraryService = libraryService;
    }

    public void save(User user)
    {
        userRepository.save(user);
    }

    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    public void eliminateLibrary(String nameLibrary, String email)
    {
        Administrator administrator =
                (Administrator) userRepository.findByEmail(email);

        Library library = libraryService.findByName(nameLibrary);

        administrator.getLibraries().remove(library);

        libraryService.eliminate(library);
    }
}
