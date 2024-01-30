package com.learning.team5shopstrumenti.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Role {

    @Id
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
