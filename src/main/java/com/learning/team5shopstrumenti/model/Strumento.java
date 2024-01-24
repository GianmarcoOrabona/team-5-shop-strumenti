package com.learning.team5shopstrumenti.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "strumenti")
public class Strumento {
    // ATTRIBUTI

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String marca;

    private String modello;

    private BigDecimal prezzo;

    @Lob
    private String foto;

    @Lob
    private String descrizione;

    // RELAZIONI
    @OneToMany(mappedBy = "strumento")
    private List<Vendita> vendite;

    @OneToMany(mappedBy = "strumento")
    private List<Assortimento> assortimenti;

    // GETTER E SETTER

}
