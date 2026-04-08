package com.pitchbridge.api.repository;

import com.pitchbridge.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails; // Import importante!
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Para validar se o e-mail já existe no cadastro
    boolean existsByEmail(String email);

    // O Spring Security usa esse método para buscar o usuário no Login
    UserDetails findByEmail(String email);
}
