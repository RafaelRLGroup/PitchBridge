package com.pitchbridge.api.service;

import com.pitchbridge.api.model.User;
import com.pitchbridge.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Busca por ID com tratamento de erro (Se não achar, lança exceção)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado, meu nobre! ID: " + id));
    }

    public User save(User user) {
        // Futuro: Validar se o e-mail já existe pra não dar erro 500 de Constraint
        return userRepository.save(user);
    }

    public User update(Long id, User userDetails) {
        User user = findById(id); // Já usa o método acima que valida o ID

        // Atualiza apenas os campos permitidos
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());

        // Se a senha vier preenchida, a gente atualiza também
        if (userDetails.getPassword() != null && !userDetails.getPassword().isBlank()) {
            user.setPassword(userDetails.getPassword());
        }

        return userRepository.save(user);
    }

    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}
