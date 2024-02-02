package com.learning.team5shopstrumenti.controller;

import com.learning.team5shopstrumenti.Dto.StrumentoDto;
import com.learning.team5shopstrumenti.interfaccie.AssortimentoRepository;
import com.learning.team5shopstrumenti.interfaccie.CategoriaRepository;
import com.learning.team5shopstrumenti.interfaccie.StrumentoRepository;
import com.learning.team5shopstrumenti.interfaccie.VenditaRepository;
import com.learning.team5shopstrumenti.model.Assortimento;
import com.learning.team5shopstrumenti.model.Categoria;
import com.learning.team5shopstrumenti.model.Strumento;
import com.learning.team5shopstrumenti.model.Vendita;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StrumentoRepository strumentoRepository;
    @Autowired
    private AssortimentoRepository assortimentoRepository;

    @Autowired
    private VenditaRepository venditaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public String index(Model model) {
        List<Strumento> strumenti = strumentoRepository.findAll();
        List<Assortimento> assortimenti=assortimentoRepository.findAll();
        model.addAttribute("strumenti", strumenti);
        return "admin/home";
        }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Strumento> result=strumentoRepository.findById(id);
        if(result.isPresent()) {
            model.addAttribute("strumento",result.get());
            model.addAttribute("categoriaList", categoriaRepository.findAll());
            return "admin/edit";
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lo strumento con id " + id + " non è stato trovato");
        }
    }

    @PostMapping("/edit/{id}")
    public String updateStrumento (@PathVariable Integer id, @Valid @ModelAttribute("strumento") Strumento formStrumento, BindingResult bindingResult) {
        Optional<Strumento> strumento = strumentoRepository.findById(formStrumento.getId());
        if (strumento.isPresent()) {
            Strumento strumentoEdit = strumento.get();
            if (bindingResult.hasErrors()) {
                return "admin/edit";
            }
            formStrumento.setFoto(strumentoEdit.getFoto());
            Strumento savedStrumento = strumentoRepository.save(formStrumento);
            return "redirect:/admin";

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lo strumento con id " + id + " non è stato trovato");
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        StrumentoDto strumentoDto  = new StrumentoDto();
        model.addAttribute("strumento", strumentoDto);
        model.addAttribute("categoriaList", categoriaRepository.findAll());
        return "admin/create";

    }

    @PostMapping("/create")
    public String store(@Valid  @ModelAttribute("strumento") StrumentoDto formStrumento, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/create";
        } else {
            model.addAttribute("categoriaList", categoriaRepository.findAll());
            Strumento strumento = new Strumento();
            strumento.setFoto(formStrumento.getFoto());
            strumento.setDescrizione(formStrumento.getDescrizione());
            strumento.setPrezzo(formStrumento.getPrezzo());
            strumento.setMarca(formStrumento.getMarca());
            strumento.setModello(formStrumento.getModello());
            strumento.setCategorie(formStrumento.getCategorie());
            Strumento saveStrumento = strumentoRepository.save(strumento);
            Assortimento assortimento = new Assortimento();
            assortimento.setQuantita(formStrumento.getQuantita());
            assortimento.setData(LocalDate.now());
            assortimento.setStrumento(saveStrumento);
            assortimentoRepository.save(assortimento);
            return "redirect:/admin" ;
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id,Model model) {
        Optional<Strumento> result=strumentoRepository.findById(id);
        if(result.isPresent()) {
            strumentoRepository.deleteById(id);
            return "redirect:/admin";

        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lo strumento con id: " + id + " non è stato trovato");
        }

    }



    @GetMapping("/vendite")
    public String vendite(Model model) {
        List<Vendita> vendite = venditaRepository.findAll();
        model.addAttribute("vendite", vendite);
        return "admin/vendite";
    }
}
