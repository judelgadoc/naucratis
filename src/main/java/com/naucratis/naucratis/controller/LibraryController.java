package com.naucratis.naucratis.controller;

import com.naucratis.naucratis.model.dto.LibraryRequestDto;
import com.naucratis.naucratis.service.LibraryService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @GetMapping(path = "/new")
    public String newLibrary(){
        return "library/new_library";
    }

    @GetMapping(path = "/edit")
    public String getEditLibrary() {
        return "library/edit_library";
    }

    @GetMapping(path="/update")
    public String getUpdateLibrary() {
        return "library/update_library";
    }

    @PostMapping(path = "/new")
    public String addLibrary(@ModelAttribute("library") LibraryRequestDto libraryRequest) {
        try {
            libraryService.save(libraryRequest);
        }catch (ConstraintViolationException exception) {
            System.out.println(exception.getConstraintName());
            return "redirect:/library/new?namealreadyexists";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("" + e.getCause());
            return "redirect:/library/new?failure";
        }
        return "redirect:/library/new?success";
    }

    @PutMapping(path = "/edit")
    public ResponseEntity<?> updateLibrary() {
        return null;
    }

    @DeleteMapping(path = "/edit")
    public ResponseEntity<?> deleteLibrary() {
        return null;
    }

    @ModelAttribute("library")
    public LibraryRequestDto libraryRequestDto() {
        return new LibraryRequestDto();
    }
}
