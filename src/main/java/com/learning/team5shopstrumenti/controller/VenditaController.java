package com.learning.team5shopstrumenti.controller;

import com.learning.team5shopstrumenti.interfaccie.AssortimentoRepository;
import com.learning.team5shopstrumenti.interfaccie.StrumentoRepository;
import com.learning.team5shopstrumenti.interfaccie.VenditaRepository;
import com.learning.team5shopstrumenti.model.Assortimento;
import com.learning.team5shopstrumenti.model.Strumento;
import com.learning.team5shopstrumenti.model.Vendita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/vendite")
public class VenditaController {

    @Autowired
    VenditaRepository venditaRepository;

    @Autowired
    AssortimentoRepository assortimentoRepository;


    @PostMapping
    public String store(@ModelAttribute("vendita") Vendita vendita, Model model) {
        Vendita savedVendita = venditaRepository.save(vendita);
        model.addAttribute("foto", vendita.getStrumento().getFoto());
        return "vendite/show";
    }
}
