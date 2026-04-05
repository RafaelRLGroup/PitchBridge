package com.pitchbridge.api.repository;

import com.pitchbridge.api.model.Dream;
import com.pitchbridge.api.model.Category;
import org.springframework.data.domain.Page; // Importante: usar o do Spring!
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DreamRepository extends JpaRepository<Dream, Long> {

    // Agora retorna Page e recebe Pageable para suportar busca paginada
    Page<Dream> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    // Listagem por categoria também paginada
    Page<Dream> findByCategory(Category category, Pageable pageable);

    // O Top 5 continua como List, pois é um ranking fixo e pequeno (não precisa paginar)
    List<Dream> findTop5ByOrderByCurrentAmountDesc();

    @Query("SELECT SUM(d.currentAmount) FROM Dream d")
    BigDecimal sumAllCurrentAmounts();
}
