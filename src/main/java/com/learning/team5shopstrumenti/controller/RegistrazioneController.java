package com.learning.team5shopstrumenti.controller;

import com.learning.team5shopstrumenti.interfaccie.UserRepository;
import com.learning.team5shopstrumenti.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registrazione")
public class RegistrazioneController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String index() {
        return "login/registrati";
    }

    @PostMapping
    public String signUp(@ModelAttribute User formUser) {
        User user=new User();
        user.setFirstName(formUser.getFirstName());
        user.setLastName(formUser.getLastName());
        user.setEmail(formUser.getEmail());
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //String pswencode = encoder.encode(formUser.getPassword());
        user.setPassword(formUser.getPassword());
        userRepository.save(user);
        return "redirect:/strumenti";

    }

}
