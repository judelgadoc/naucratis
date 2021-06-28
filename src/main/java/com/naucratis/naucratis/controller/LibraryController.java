package com.naucratis.naucratis.controller;

import com.naucratis.naucratis.model.dto.LibraryRequestDto;
import com.naucratis.naucratis.model.library.Library;
import com.naucratis.naucratis.service.LibraryService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/library")
public class LibraryController {

    private final String EDIT_LIBRARY_MESSAGE_MODEL = "editLibraryMessage";
    private final String EDIT_LIBRARY_SUCCESS = "Librería actualizada con éxito";
    private final String DELETE_LIBRARY_SUCCESS = "Librería eliminada con éxito";
    private final String UNEXPECTED_ERROR = "Error no esperado";

    @Autowired
    LibraryService libraryService;

    @GetMapping(path = "/new")
    public String newLibrary(){
        return "library/new_library";
    }

    @GetMapping(path = "/edit")
    public String getEditLibrary(Model model) {
        model.addAttribute("libraries", libraryService.findAll());
        return "library/edit_library";
    }

    @GetMapping(path="/get", params="libraryId")
    @ResponseBody
    public Optional<Library> getLibraryById(@RequestParam(name="libraryId") long id) {
        return libraryService.getLibraryById(id);
    }

    @GetMapping(path="/update", params="libraryId")
    public String getUpdateLibrary(@RequestParam(name="libraryId") long libraryId, Model model) {
        model.addAttribute("library", libraryService.getLibraryById(libraryId));
        return "library/update_library";
    }

    @GetMapping(path="/delete", params="libraryId")
    public String getDeleteLibrary(@RequestParam(name="libraryId") long libraryId, Model model) {
        try {
            libraryService.deleteLibraryById(libraryId);
            model.addAttribute(EDIT_LIBRARY_MESSAGE_MODEL, DELETE_LIBRARY_SUCCESS);
            return "redirect:/library/edit?success";
        } catch (Exception e) {
            model.addAttribute(EDIT_LIBRARY_MESSAGE_MODEL, UNEXPECTED_ERROR);
            return "redirect:/library/edit?failure";
        }
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

    @PostMapping(path = "/update")
    public String updateLibrary(@ModelAttribute("library") LibraryRequestDto libraryRequest, Model model) {
        try {
            libraryService.updateLibrary(libraryRequest);
            model.addAttribute(EDIT_LIBRARY_MESSAGE_MODEL, EDIT_LIBRARY_SUCCESS);
            return "redirect:/library/edit?success";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            model.addAttribute(EDIT_LIBRARY_MESSAGE_MODEL, UNEXPECTED_ERROR);
            return "redirect:/library/edit?failure";
        }
    }

    @ModelAttribute("library")
    public LibraryRequestDto libraryRequestDto() {
        return new LibraryRequestDto();
    }
}
