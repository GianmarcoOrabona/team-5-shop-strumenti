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
