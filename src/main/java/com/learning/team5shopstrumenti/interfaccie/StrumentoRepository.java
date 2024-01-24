package com.learning.team5shopstrumenti.interfaccie;

import com.learning.team5shopstrumenti.model.Strumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StrumentoRepository extends JpaRepository<Strumento, Integer> {
}
