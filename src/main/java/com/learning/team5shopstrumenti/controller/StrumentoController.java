package com.learning.team5shopstrumenti.controller;

import com.learning.team5shopstrumenti.interfaccie.StrumentoRepository;
import com.learning.team5shopstrumenti.model.Strumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/strumenti")
public class StrumentoController {

    @Autowired
    private StrumentoRepository strumentoRepository;

    @GetMapping
    public String index() {
        List<Strumento> strumenti = strumentoRepository.findAll();
        return "strumenti/list";
    }
}
