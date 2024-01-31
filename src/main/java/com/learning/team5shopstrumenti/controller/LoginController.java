package com.learning.team5shopstrumenti.controller;

import com.learning.team5shopstrumenti.interfaccie.UserRepository;
import com.learning.team5shopstrumenti.security.DatabaseUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/custom-login")
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DatabaseUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping
    public String showLoginForm() {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return "redirect:/";
        }
        return "login/login";
    }
}

