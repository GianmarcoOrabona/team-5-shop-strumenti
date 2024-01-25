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
@RequestMapping("/admin/strumenti")
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
        return "strumenti/list";
    }

    @GetMapping("/admin/strumenti/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Strumento> result=strumentoRepository.findById(id);
        if(result.isPresent()) {
            model.addAttribute("strumento",result.get());
            return "strumenti/edit";
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "strumento with id " + id + " not found");
        }
    }

    @PostMapping("/admin/strumenti/edit/{id}")
    public String updateStrumento (@PathVariable Integer id, @Valid @ModelAttribute("strumento") Strumento formStrumento, BindingResult bindingResult, Model model ) {
        Optional<Strumento> strumento = strumentoRepository.findById(formStrumento.getId());
        if (strumento.isPresent()) {
            Strumento strumentoEdit = strumento.get();
            if (bindingResult.hasErrors()) {
                return "strumenti/edit";
            }
            formStrumento.setFoto(strumentoEdit.getFoto());
            Strumento savedStrumento = strumentoRepository.save(formStrumento);
            return "redirect:/adimin/strumenti";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Strumento with id " + id + " not found");
        }
    }

    @GetMapping("/admin/strumenti/create")
    public String create(Model model) {
        Strumento strumento = new Strumento();
        model.addAttribute("strumento", strumento);
        return "strumenti/create";
    }

    @PostMapping("/admin/strumenti/create")
    public String store(@Valid @ModelAttribute("strumento") Strumento formStrumento, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "strumenti/create";
        } else {
            Strumento strumentoSalvato = strumentoRepository.save(formStrumento);
            return "redirect:/strumenti/show/" + strumentoSalvato.getId();
        }
    }

    @PostMapping("/admin/strumenti/delete/{id}")
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
