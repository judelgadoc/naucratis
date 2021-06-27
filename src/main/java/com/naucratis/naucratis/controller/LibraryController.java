package com.naucratis.naucratis.controller;

import com.naucratis.naucratis.exception.LibraryNotFoundException;
import com.naucratis.naucratis.model.dto.LibraryRequestDto;
import com.naucratis.naucratis.model.library.Library;
import com.naucratis.naucratis.service.LibraryService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/library")
public class LibraryController {

    private final String EDIT_LIBRARY_MESSAGE_MODEL = "editLibraryMessage";
    private final String DELETE_LIBRARY_MESSAGE_MODEL = "deleteLibraryMessage";

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

    @PutMapping(path = "/update")
    public String updateLibrary(@ModelAttribute("library") LibraryRequestDto libraryRequest) {
        try {
            System.out.println("Trying update on library");
            System.out.println("Request: ");
            System.out.println(libraryRequest.toString());
            libraryService.updateLibrary(libraryRequest);
            //model.addAttribute(EDIT_LIBRARY_MESSAGE_MODEL, new String("Librería actualizada con éxito"));
            return "redirect:/library/edit?success";
        } catch (LibraryNotFoundException e) {
            //model.addAttribute(EDIT_LIBRARY_MESSAGE_MODEL, e.getMessage());
            return "redirect:/library/edit?failure";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            //model.addAttribute(EDIT_LIBRARY_MESSAGE_MODEL, new String("Error no esperado"));
            return "redirect:/library/edit?failure";
        }
    }

    @DeleteMapping(path = "/edit", params = "libraryId")
    public String deleteLibrary(@PathVariable(name="libraryId") Long libraryId, Model model) {
        model.addAttribute(DELETE_LIBRARY_MESSAGE_MODEL, new String("Librería eliminada con éxito"));
        return "redirect:/library/edit?success";
    }

    @ModelAttribute("library")
    public LibraryRequestDto libraryRequestDto() {
        return new LibraryRequestDto();
    }
}
