package com.pitchbridge.api.repository;

import com.pitchbridge.api.model.Dream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DreamRepository extends JpaRepository<Dream, Long> {
    // No futuro, podemos criar métodos customizados aqui,
    // tipo: findByTitleContaining(String keyword)
}
