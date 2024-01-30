package com.learning.team5shopstrumenti.interfaccie;

import com.learning.team5shopstrumenti.model.Categoria;
import com.learning.team5shopstrumenti.model.Strumento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
    Optional<Categoria> findByName(String categoriaName);
}
