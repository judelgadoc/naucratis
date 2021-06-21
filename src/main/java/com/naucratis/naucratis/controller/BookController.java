package com.naucratis.naucratis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.sprindframework.web.bind.annotation.GetMapping;
import org.sprindframework.ui.Model;

@Controller
public class BookController{
    @Autowired
    private IUBookRepository BookRepository;
    @GetMapping("/")
    public String form(Model model){
        model.addAttribute("book", new Book());
        return "form";
    }
    @PostMapping("/")
    public String guardar(@RequestParam(name = "file", required = false) MultipartFile portada, Book book, RedirectAttributes flash){
        if(!portada.isEmpty()){
            String ruta = "C//Temp//uploads";

            try{
                byte[] bytes = portada.getBytes();
                Path rutaAbsoluta = Paths.get(ruta+"//"+portada.getOriginalFilename());
                Files.write(rutaAbsoluta, bytes);
                book.setPortada(portada.getOriginalFilename());
            }catch (Exception e){
                // TODO: handle exception
            }
            BookRepository.save(book);
            flash.addFlashAttribute("success","Portada subida!");
        }
        return "redirect:/";
    }
    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("book", BookRepository.findAll());
        return listar;
    }
}