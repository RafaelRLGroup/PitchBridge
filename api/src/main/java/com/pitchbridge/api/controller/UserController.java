package com.pitchbridge.api.controller;

import com.pitchbridge.api.dto.UserResponseDTO;
import com.pitchbridge.api.model.User;
import com.pitchbridge.api.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 1. Listar todos (Usando DTO para segurança)
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.findAll().stream()
                .map(UserResponseDTO::new)
                .toList();
        return ResponseEntity.ok(users);
    }

    // 2. Buscar por ID (Importante para o perfil do usuário)
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(new UserResponseDTO(user));
    }

    // 3. Criar Usuário (Retornando 201 Created)
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody User user) {
        // Agora o save já devolve o DTO pronto!
        UserResponseDTO savedUserDto = userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDto);
    }

    // 4. Atualizar Usuário
        @PutMapping("/{id}")
        public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
            // O Service já devolve o DTO, então não precisamos converter aqui!
            UserResponseDTO updatedUserDto = userService.update(id, userDetails);

            return ResponseEntity.ok(updatedUserDto);
        }

    // 5. Deletar Usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}
