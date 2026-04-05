package com.pitchbridge.api.service;

import com.pitchbridge.api.dto.DreamResponseDTO;
import com.pitchbridge.api.dto.PlatformReportDTO;
import com.pitchbridge.api.model.Dream;
import com.pitchbridge.api.model.User;
import com.pitchbridge.api.repository.ContributionRepository;
import com.pitchbridge.api.repository.DreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pitchbridge.api.model.Category;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class DreamService {

    @Autowired
    private DreamRepository dreamRepository;

    @Autowired
    private UserService userService; // Injetamos o UserService para validar o criador

    // No DreamService.java
    @Autowired
    private ContributionRepository contributionRepository; // Precisamos dele para contar doações

    public PlatformReportDTO getPlatformReport() {
        Long totalDreams = dreamRepository.count();
        Long totalContributions = contributionRepository.count();
        BigDecimal totalArrecadado = dreamRepository.sumAllCurrentAmounts();

        // Tratamento para não vir null se o banco estiver vazio
        if (totalArrecadado == null) totalArrecadado = BigDecimal.ZERO;

        return new PlatformReportDTO(totalDreams, totalContributions, totalArrecadado);
    }

    @Transactional(readOnly = true)
    public Page<DreamResponseDTO> findByCategory(Category category, Pageable pageable) {
        return dreamRepository.findByCategory(category, pageable)
                .map(DreamResponseDTO::new);
    }

// O findAll você já tinha feito certinho! ✅
    @Transactional(readOnly = true)
    public Page<DreamResponseDTO> findAll(Pageable pageable) {
        return dreamRepository.findAll(pageable)
                .map(DreamResponseDTO::new);
    }

@Transactional(readOnly = true)
    public Page<DreamResponseDTO> searchByTitle(String title, Pageable pageable) {
        return dreamRepository.findByTitleContainingIgnoreCase(title, pageable)
                .map(DreamResponseDTO::new);
    }

// getTrending continua List porque é um "Top 5" fixo. ✅
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
