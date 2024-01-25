package com.learning.team5shopstrumenti.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "strumenti")
public class Strumento {
    // ATTRIBUTI

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Il nome non può essere vuoto")
    private String marca;

    @NotEmpty(message = "Il modello non può essere vuoto")
    private String modello;

    @NotNull(message = "Il prezzo non può essere vuoto")
    @DecimalMin(value = "1.00", message = "Il prezzo non può essere minore di 1.00")
    private BigDecimal prezzo;

    @Lob
    private String foto;

    @Lob
    private String descrizione;

    // RELAZIONI
    @OneToMany(mappedBy = "strumento")
    private List<Vendita> vendite;

    @OneToMany(mappedBy = "strumento", orphanRemoval = true)
    private List<Assortimento> assortimenti;

    // GETTER E SETTER

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Vendita> getVendite() {
        return vendite;
    }

    public void setVendite(List<Vendita> vendite) {
        this.vendite = vendite;
    }

    public List<Assortimento> getAssortimenti() {
        return assortimenti;
    }

    public void setAssortimenti(List<Assortimento> assortimenti) {
        this.assortimenti = assortimenti;
    }
}
