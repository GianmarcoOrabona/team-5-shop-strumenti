package com.learning.team5shopstrumenti.interfaccie;

import com.learning.team5shopstrumenti.model.Categoria;
import com.learning.team5shopstrumenti.model.Strumento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
    List<Categoria> findByName(String categoriaName);
}
