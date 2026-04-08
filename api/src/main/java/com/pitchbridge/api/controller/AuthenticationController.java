package com.pitchbridge.api.controller;

import com.pitchbridge.api.config.TokenService;
import com.pitchbridge.api.dto.AuthenticationDTO;
import com.pitchbridge.api.dto.LoginResponseDTO;
import com.pitchbridge.api.model.User;
import com.pitchbridge.api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        // 1. Cria o 'token' interno do Spring com email e senha
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

        // 2. O AuthenticationManager vai no banco (via AuthorizationService) e valida a senha
        var auth = this.authenticationManager.authenticate(usernamePassword);

        // 3. Se deu certo, gera o Token JWT para o usuário
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user) {
        if (this.userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().build();
        }

        // Criptografa a senha antes de salvar!
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);

        this.userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
