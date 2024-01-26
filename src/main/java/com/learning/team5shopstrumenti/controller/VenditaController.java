package com.learning.team5shopstrumenti.controller;

import com.learning.team5shopstrumenti.interfaccie.VenditaRepository;
import com.learning.team5shopstrumenti.model.Vendita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/vendite")
public class VenditaController {

    @Autowired
    VenditaRepository venditaRepository;

    @PostMapping
    public String store(@ModelAttribute("vendita") Vendita vendita, Model model) {
        Vendita savedVendita = venditaRepository.save(vendita);
        return "redirect:/strumenti";
    }
}
