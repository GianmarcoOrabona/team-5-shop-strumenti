package com.learning.team5shopstrumenti.controller;

import com.learning.team5shopstrumenti.Dto.UtenteDto;
import com.learning.team5shopstrumenti.interfaccie.RoleRepository;
import com.learning.team5shopstrumenti.interfaccie.UserRepository;
import com.learning.team5shopstrumenti.model.Assortimento;
import com.learning.team5shopstrumenti.model.Role;
import com.learning.team5shopstrumenti.model.Strumento;
import com.learning.team5shopstrumenti.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/registrazione")
public class RegistrazioneController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String index() {
        return "login/registrati";
    }

    @PostMapping
    public String signUp(@ModelAttribute UtenteDto formUser, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "/strumenti";
        } else {
            model.addAttribute("role", roleRepository.findAll());
            User user = new User();
            user.setEmail(formUser.getEmail());
            user.setFirstName(formUser.getFirstName());
            user.setPassword(formUser.getPassword());
            user.setLastName(formUser.getLastName());
            userRepository.save(user);
            Role role = new Role();
            role.setName(formUser.getRole());
            roleRepository.save(role);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String pswencode = encoder.encode(formUser.getPassword());
            user.setPassword("{bcrypt}" + pswencode);
            userRepository.save(user);
            return "redirect:/strumenti";
        }

    }

}
