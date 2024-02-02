package com.learning.team5shopstrumenti.controller;

import com.learning.team5shopstrumenti.interfaccie.AssortimentoRepository;
import com.learning.team5shopstrumenti.interfaccie.StrumentoRepository;
import com.learning.team5shopstrumenti.model.Assortimento;
import com.learning.team5shopstrumenti.model.Strumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/assortimento")
class AssortimentoController {

    @Autowired
    private AssortimentoRepository assortimentoRepository;

    @Autowired
    private StrumentoRepository strumentoRepository;

    @GetMapping
    public String index(Model model) {
        List<Assortimento> assortimenti = assortimentoRepository.findAll();
        model.addAttribute("assortimenti", assortimenti);
        return "admin/assortimenti";
    }

    @GetMapping("/create/{id}")
    public String form(@PathVariable Integer id,Model model) {
        Optional<Strumento> result=strumentoRepository.findById(id);
        Strumento strumento= result.get();
        Assortimento assortimento=new Assortimento();
        assortimento.setData(LocalDate.now());
        assortimento.setStrumento(strumento);
        model.addAttribute("assortimento",assortimento);
        model.addAttribute("strumento",strumento);
        return "admin/restock";

    }

    @PostMapping("/create/{id}")
    public String create(@PathVariable Integer id,@ModelAttribute Assortimento formAssortimento,Model model) {
        Optional<Strumento> result= strumentoRepository.findById(id);
        Assortimento assortimento= new Assortimento();
        assortimento.setStrumento(result.get());
        assortimento.setQuantita(formAssortimento.getQuantita());
        assortimento.setData(LocalDate.now());
        assortimentoRepository.save(assortimento);
        List<Strumento> list=strumentoRepository.findAll();
        model.addAttribute("strumenti",list);
        return "admin/home";

    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        Optional<Assortimento> result=assortimentoRepository.findById(id);
        if(result.isPresent()) {
            assortimentoRepository.deleteById(id);
            return "redirect:/admin/assortimento";

        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L' Assortimento con questo id: " + id + " non e' stato trovato");
        }

    }
}
