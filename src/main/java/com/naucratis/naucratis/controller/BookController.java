package com.naucratis.naucratis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.naucratis.naucratis.model.Book;
import com.naucratis.naucratis.repository.BookRepository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class BookController{
    @Autowired
    private BookRepository datosLibro;

    @GetMapping("/")
    public String form(Model model){
        model.addAttribute("libro", new Book());
        return "form";
    }

    @PostMapping("/")
    public String guardar(@RequestParam(name = "file", required = false) MultipartFile portada, Book book, RedirectAttributes flash){
        if(!portada.isEmpty()){
            String ruta = "C//Temp//uploads";

            try{
                byte[] bytes = portada.getBytes();
                Path rutaAbsoluta = Paths.get(ruta + "//" + portada.getOriginalFilename());
                Files.write(rutaAbsoluta, bytes);
                book.setCover(portada.getOriginalFilename());
            }catch (Exception e){
                // TODO: handle exception
            }
            datosLibro.save(book);
            flash.addFlashAttribute("success","Portada subida!");
        }
        return "redirect:/";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("libro", datosLibro.findAll());
        return "inventario";
    }
}

