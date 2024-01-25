package com.learning.team5shopstrumenti.controller;

import com.learning.team5shopstrumenti.interfaccie.StrumentoRepository;
import com.learning.team5shopstrumenti.model.Strumento;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;



@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StrumentoRepository strumentoRepository;

    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String searchKeyword, Model model) {

        List<Strumento> strumenti;
        if (searchKeyword != null) {
            strumenti = strumentoRepository.findByMarcaContaining(searchKeyword);
        } else {
            strumenti = strumentoRepository.findAll();
        }
        model.addAttribute("area", "admin");
        model.addAttribute("strumenti", strumenti);
        model.addAttribute("preloadSearch", searchKeyword);
        return "admin/home";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Strumento> result=strumentoRepository.findById(id);
        if(result.isPresent()) {
            model.addAttribute("strumento",result.get());
            return "admin/edit";
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "strumento with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String updateStrumento (@PathVariable Integer id, @Valid @ModelAttribute("strumento") Strumento formStrumento, BindingResult bindingResult) {
        Optional<Strumento> strumento = strumentoRepository.findById(formStrumento.getId());
        if (strumento.isPresent()) {
            Strumento strumentoEdit = strumento.get();
            if (bindingResult.hasErrors()) {
                return "admin/edit";
            }
            formStrumento.setFoto(strumentoEdit.getFoto());
            Strumento savedStrumento = strumentoRepository.save(formStrumento);
            return "redirect:/admin";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Strumento with id " + id + " not found");
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        Strumento strumento = new Strumento();
        model.addAttribute("strumento", strumento);
        return "admin/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("strumento") Strumento formStrumento, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/create";
        } else {
            Strumento strumentoSalvato = strumentoRepository.save(formStrumento);
            return "redirect:/admin" ;
        }
    }

    @PostMapping("/strumenti/delete/{id}")
    public String delete(@PathVariable Integer id,Model model) {
        Optional<Strumento> result=strumentoRepository.findById(id);
        if(result.isPresent()) {
            strumentoRepository.deleteById(id);
            return "redirect:/strumenti";

        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + id + " not found");
        }

    }
}
