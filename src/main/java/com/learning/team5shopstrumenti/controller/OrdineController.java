package com.learning.team5shopstrumenti.controller;

import com.learning.team5shopstrumenti.interfaccie.UserRepository;
import com.learning.team5shopstrumenti.interfaccie.VenditaRepository;
import com.learning.team5shopstrumenti.model.User;
import com.learning.team5shopstrumenti.model.Vendita;
import com.learning.team5shopstrumenti.security.DatabaseUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/ordini")
public class OrdineController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping
    public String shop(Model model, Authentication authentication){
        String userEmail = authentication.getName();
        Optional<User> user = userRepository.findByEmail(userEmail);
        if (user.isPresent()) {
            model.addAttribute("userLoggato", user.get());
            return "vendite/ordine";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
        }
    }
}
