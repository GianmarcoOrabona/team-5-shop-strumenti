package com.learning.team5shopstrumenti.controller;

import com.learning.team5shopstrumenti.interfaccie.StrumentoRepository;
import com.learning.team5shopstrumenti.model.Strumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/strumenti")
public class StrumentoController {

    @Autowired
    private StrumentoRepository strumentoRepository;

    @GetMapping
    public String index(Model model) {
        // Listaa strumenti
        List<Strumento> strumenti = strumentoRepository.findAll();
        model.addAttribute("strumenti", strumenti);
        return "strumenti/list";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Strumento> result=strumentoRepository.findById(id);
        if(result.isPresent()) {
            Strumento obj= result.get();
            Strumento strumento=new Strumento();
            strumento.setFoto(obj.getFoto());
            strumento.setMarca(obj.getMarca());
            strumento.setDescrizione(obj.getDescrizione());
            strumento.setModello(obj.getModello());
            strumento.setPrezzo(obj.getPrezzo());
            model.addAttribute("strumento",strumento);
            return "strumenti/edit";
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "strumento with id " + id + " not found");
        }
    }
}