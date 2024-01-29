package com.learning.team5shopstrumenti.Dto;

import com.learning.team5shopstrumenti.model.Categoria;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

public class StrumentoDto {

private String marca;

private BigDecimal prezzo;

private String foto;

private String modello;

private Integer quantita;

private String descrizione;

@ManyToOne
private Categoria categorie;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Categoria getCategorie() {
        return categorie;
    }

    public void setCategorie(Categoria categorie) {
        this.categorie = categorie;
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

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
