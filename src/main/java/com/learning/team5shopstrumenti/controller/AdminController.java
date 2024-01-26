package com.learning.team5shopstrumenti.controller;

import com.learning.team5shopstrumenti.interfaccie.AssortimentoRepository;
import com.learning.team5shopstrumenti.interfaccie.StrumentoRepository;
import com.learning.team5shopstrumenti.model.Assortimento;
import com.learning.team5shopstrumenti.model.Strumento;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StrumentoRepository strumentoRepository;
    @Autowired
    private AssortimentoRepository assortimentoRepository;

    @GetMapping
    public String index(Model model) {
        List<Strumento> strumenti = strumentoRepository.findAll();
        model.addAttribute("strumenti", strumenti);
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
        Assortimento assortimento=new Assortimento();
        assortimento.setData(LocalDate.now());
        model.addAttribute("strumento", strumento);
        model.addAttribute("assortimento",assortimento);
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

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id,Model model) {
        Optional<Strumento> result=strumentoRepository.findById(id);
        if(result.isPresent()) {
            strumentoRepository.deleteById(id);
            return "redirect:/admin";

        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lo strumento con questo id: " + id + " non e' stato trovato");
        }

    }
}
