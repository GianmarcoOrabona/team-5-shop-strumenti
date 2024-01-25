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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/strumenti")
public class StrumentoController {

    @Autowired
    private StrumentoRepository strumentoRepository;

    @Autowired
    private AssortimentoRepository assortimentoRepository;


    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String searchKeyword, Model model) {

        List<Strumento> strumenti;
        if (searchKeyword != null) {
            strumenti = strumentoRepository.findByMarcaContaining(searchKeyword);
        } else {
            strumenti = strumentoRepository.findAll();
        }
        model.addAttribute("area", "public");
        model.addAttribute("strumenti", strumenti);
        model.addAttribute("preloadSearch", searchKeyword);
        return "strumenti/list";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        List<Integer> lista = new ArrayList<>();
        int cont = 0;
        Optional<Strumento> result = strumentoRepository.findById(id);
        if (result.isPresent()) {
            for (int i = 0; i < result.get().getAssortimenti().get(0).getQuantita(); i++) {
                cont++;
                lista.add(cont);
            }

            Strumento strumento = result.get();
            model.addAttribute("strumento", strumento);
            model.addAttribute("array", lista);
            return "strumenti/show";
        }

        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Strumento with id " + id + " not found");
        }
    }

    @GetMapping("/checkout")
    public String checkout( Model model) {
        return "strumenti/checkout";
    }

    @PostMapping("/buy/{id}")
    public String buy(@PathVariable Integer id, Model model) {
        Optional<Strumento> result = strumentoRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("strumento", result.get());
            LocalDate today = LocalDate.now();

                return "strumenti/checkout";
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Strumento with id " + id + " not found");
            }
    }

}
