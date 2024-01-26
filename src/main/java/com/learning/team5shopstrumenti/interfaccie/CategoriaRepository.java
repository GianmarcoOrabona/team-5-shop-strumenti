package com.learning.team5shopstrumenti.interfaccie;

import com.learning.team5shopstrumenti.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
}
