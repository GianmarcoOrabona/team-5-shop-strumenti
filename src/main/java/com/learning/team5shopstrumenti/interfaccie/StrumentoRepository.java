package com.learning.team5shopstrumenti.interfaccie;

import com.learning.team5shopstrumenti.model.Strumento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StrumentoRepository extends JpaRepository<Strumento, Integer> {
    List<Strumento> findByMarcaContaining (String search);

}
