package com.learning.team5shopstrumenti.controller;

import com.learning.team5shopstrumenti.comparator.StrumentoDataVenditaComparator;
import com.learning.team5shopstrumenti.interfaccie.AssortimentoRepository;
import com.learning.team5shopstrumenti.interfaccie.CategoriaRepository;
import com.learning.team5shopstrumenti.interfaccie.StrumentoRepository;
import com.learning.team5shopstrumenti.interfaccie.VenditaRepository;
import com.learning.team5shopstrumenti.model.Categoria;
import com.learning.team5shopstrumenti.model.Strumento;
import com.learning.team5shopstrumenti.model.Vendita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/strumenti")
public class StrumentoController {

    @Autowired
    private StrumentoRepository strumentoRepository;

    @Autowired
    private AssortimentoRepository assortimentoRepository;

    @Autowired
    private VenditaRepository venditaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String searchKeyword,@RequestParam(name = "searchCategoria", required = false) String searchCategoria, Model model) {
        List<Strumento> strumenti;
        if (searchKeyword != null) {
            strumenti = strumentoRepository.findByMarcaContainingOrModelloContaining(searchKeyword, searchKeyword);
        } else if (searchCategoria != null) {
            Optional<Categoria> categoria = categoriaRepository.findByName(searchCategoria);
             if (categoria.isPresent()) {
                 strumenti = strumentoRepository.findByCategorie(categoria.get());
             } else {
                 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria non trovata");
             }

        } else {
            strumenti = strumentoRepository.findAll();
        }
        model.addAttribute("strumenti", strumenti);
        model.addAttribute("preloadSearch", searchKeyword);
        List<Strumento> topStrumenti = getTopStrumenti();
        model.addAttribute("topStrumenti", topStrumenti);
        return "strumenti/list";
    }

    private List<Strumento> getTopStrumenti() {
        List<Strumento> strumentoVenduto = strumentoRepository.findAll();
        Collections.sort(strumentoVenduto, Collections.reverseOrder(new StrumentoDataVenditaComparator()));
        return strumentoVenduto.subList(0, 5);
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        List<Integer> lista = new ArrayList<>();
        Vendita vendita = new Vendita();
        int cont = 0;
        Optional<Strumento> result = strumentoRepository.findById(id);
        if (result.isPresent()) {
            for (int i = 0; i < result.get().getAssortimenti().get(0).getQuantita(); i++) {
                cont++;
                lista.add(cont);
            }

            Strumento strumento = result.get();
            vendita.setStrumento(strumento);
            vendita.setData(LocalDate.now());
            model.addAttribute("vendita", vendita);
            model.addAttribute("strumento", strumento);
            model.addAttribute("array", lista);
            return "strumenti/show";
        }

        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lo strumento con id " + id + " non è stato trovato");
        }
    }


    @GetMapping("/strumenti")
    public String listaOggetti(Model model, @RequestParam(defaultValue = "asc") String ordine) {
        List<Strumento> strumento = strumentoRepository.findAll();
        if (ordine.equals("asc")) {
            strumento.sort(Comparator.comparing(Strumento::getPrezzo));
        } else if (ordine.equals("desc")) {
            strumento.sort(Comparator.comparing(Strumento::getPrezzo).reversed());
        }
        model.addAttribute("strumenti1", strumento);
        return "strumenti/list";
    }




}
