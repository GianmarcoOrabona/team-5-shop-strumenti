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
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/registrazione")
public class RegistrazioneController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String index(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login/registrati";
    }

    @PostMapping
    public String signUp(@ModelAttribute User formUser, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "/strumenti";
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String pswencode = encoder.encode(formUser.getPassword());
            formUser.setPassword("{bcrypt}" + pswencode);
            Role roleUser = new Role();
            roleUser.setName("user");
            Set<Role> roleUserSet = new HashSet<>();
            roleUserSet.add(roleUser);
            formUser.setRoleSet(roleUserSet);
            userRepository.save(formUser);
            return "redirect:/strumenti";
        }

    }

}
