package com.pitchbridge.api.service;

import com.pitchbridge.api.dto.DreamResponseDTO;
import com.pitchbridge.api.model.Dream;
import com.pitchbridge.api.model.User;
import com.pitchbridge.api.repository.DreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DreamService {

    @Autowired
    private DreamRepository dreamRepository;

    @Autowired
    private UserService userService; // Injetamos o UserService para validar o criador

    @Transactional(readOnly = true)
    public List<DreamResponseDTO> findAll() {
        return dreamRepository.findAll()
                .stream()
                .map(DreamResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DreamResponseDTO> searchByTitle(String title) {
        return dreamRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(DreamResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DreamResponseDTO> getTrendingDreams() {
        return dreamRepository.findTop5ByOrderByCurrentAmountDesc()
                .stream()
                .map(DreamResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public DreamResponseDTO save(Dream dream) {
        // 1. Buscamos o usuário completo para garantir que ele existe e pegar o nome real
        User creator = userService.findById(dream.getCreator().getId());

        // 2. Associamos o usuário completo ao sonho
        dream.setCreator(creator);

        // 3. Garantimos que o sonho comece com 0.00 se for novo
        if (dream.getCurrentAmount() == null) {
            dream.setCurrentAmount(BigDecimal.ZERO);
        }

        // 4. Salvamos no banco
        Dream savedDream = dreamRepository.save(dream);

        // 5. Retornamos o DTO já populado com o nome do criador
        return new DreamResponseDTO(savedDream);
    }

    @Transactional(readOnly = true)
    public Dream findById(Long id) {
        return dreamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sonho não encontrado, meu nobre! ID: " + id));
    }
}
