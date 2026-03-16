package com.pitchbridge.api.repository;

import com.pitchbridge.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Aqui o Spring Data faz a mágica: ele já te dá save(), findAll(), findById(), delete()...
    // Tudo de graça, sem você escrever uma linha de código!
}
