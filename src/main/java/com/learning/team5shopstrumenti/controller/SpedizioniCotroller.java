package com.learning.team5shopstrumenti.controller;

import com.learning.team5shopstrumenti.model.Assortimento;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/spedizioni")
public class SpedizioniCotroller {
    @GetMapping
    public String index(Model model) {
        return "strumenti/spedizioni";
    }
}
