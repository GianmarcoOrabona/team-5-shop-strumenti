package com.learning.team5shopstrumenti.interfaccie;

import com.learning.team5shopstrumenti.model.Assortimento;
import com.learning.team5shopstrumenti.model.Vendita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenditaRepository extends JpaRepository<Vendita, Integer> {
}
