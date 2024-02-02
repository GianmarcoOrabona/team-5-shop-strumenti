package com.learning.team5shopstrumenti.controller;

import com.learning.team5shopstrumenti.interfaccie.AssortimentoRepository;
import com.learning.team5shopstrumenti.interfaccie.StrumentoRepository;
import com.learning.team5shopstrumenti.interfaccie.UserRepository;
import com.learning.team5shopstrumenti.interfaccie.VenditaRepository;
import com.learning.team5shopstrumenti.model.Assortimento;
import com.learning.team5shopstrumenti.model.Strumento;
import com.learning.team5shopstrumenti.model.User;
import com.learning.team5shopstrumenti.model.Vendita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vendite")
public class VenditaController {

    @Autowired
    VenditaRepository venditaRepository;

    @Autowired
    public UserRepository userRepository;


    @PostMapping
    public String store(@ModelAttribute("vendita") Vendita vendita, Model model, Authentication authentication) {
        String userEmail = authentication.getName();
        Optional<User> user = userRepository.findByEmail(userEmail);
        if (user.isPresent()) {
            vendita.setUtenti(user.get());
            Vendita savedVendita = venditaRepository.save(vendita);
            model.addAttribute("vendita", savedVendita);
            model.addAttribute("foto", vendita.getStrumento().getFoto());
            return "vendite/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
        }
    }
}
