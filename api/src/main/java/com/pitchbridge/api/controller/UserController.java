package com.pitchbridge.api.controller;

import com.pitchbridge.api.model.User;
import com.pitchbridge.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Diz pro Spring que isso é uma rota de API
@RequestMapping("/users") // Todas as rotas aqui começam com /users
public class UserController {

    @Autowired // Injeta o repositório que a gente criou
    private UserRepository userRepository;

    // Listar todos os usuários (Pra gente testar se tem alguém lá)
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Criar um novo usuário
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }
}
