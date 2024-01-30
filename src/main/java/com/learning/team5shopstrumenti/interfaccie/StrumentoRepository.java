package com.learning.team5shopstrumenti.interfaccie;

import com.learning.team5shopstrumenti.model.Categoria;
import com.learning.team5shopstrumenti.model.Strumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StrumentoRepository extends JpaRepository<Strumento, Integer> {
    List<Strumento> findByMarcaContainingOrModelloContaining (String searchMarca, String searchModello);

    List<Strumento> findByCategorie(Categoria categoria);

}
