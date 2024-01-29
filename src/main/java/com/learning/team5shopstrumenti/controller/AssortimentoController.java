package com.learning.team5shopstrumenti.controller;

import com.learning.team5shopstrumenti.interfaccie.AssortimentoRepository;
import com.learning.team5shopstrumenti.model.Assortimento;
import com.learning.team5shopstrumenti.model.Strumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/assortimento")
class AssortimentoController {

    @Autowired
    private AssortimentoRepository assortimentoRepository;

    @GetMapping
    public String index(Model model) {
        List<Assortimento> assortimenti = assortimentoRepository.findAll();
        model.addAttribute("assortimenti", assortimenti);
        return "admin/assortimenti";
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        Optional<Assortimento> result=assortimentoRepository.findById(id);
        if(result.isPresent()) {
            assortimentoRepository.deleteById(id);
            return "redirect:/admin";

        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L Assortimento con questo id: " + id + " non e' stato trovato");
        }

    }
}
