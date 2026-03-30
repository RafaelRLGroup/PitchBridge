package com.pitchbridge.api.repository;

import com.pitchbridge.api.model.Dream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DreamRepository extends JpaRepository<Dream, Long> {
// Busca sonhos que contenham a palavra no título (Case Insensitive)
    List<Dream> findByTitleContainingIgnoreCase(String title);

    // Busca os sonhos que já arrecadaram algo, ordenados pelo que tem mais dinheiro
    List<Dream> findTop5ByOrderByCurrentAmountDesc();
    @Query("SELECT SUM(d.currentAmount) FROM Dream d")
    BigDecimal sumAllCurrentAmounts();
}

