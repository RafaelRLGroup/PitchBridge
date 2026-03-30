package com.pitchbridge.api.service;

import com.pitchbridge.api.dto.ContributionResponseDTO;
import com.pitchbridge.api.dto.UserRankingDTO;
import com.pitchbridge.api.model.Contribution;
import com.pitchbridge.api.model.Dream;
import com.pitchbridge.api.repository.ContributionRepository;
import com.pitchbridge.api.repository.DreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContributionService {

    @Autowired
    private ContributionRepository contributionRepository;

    @Autowired
    private DreamRepository dreamRepository;

    @Autowired
    private UserService userService;

@Transactional
    public Contribution createContribution(Contribution contribution) {
        // 1. Validar Doador
        userService.findById(contribution.getDonor().getId());

        // 2. Validar e Carregar o Sonho
        Dream dream = dreamRepository.findById(contribution.getDream().getId())
                .orElseThrow(() -> new RuntimeException("Sonho não encontrado!"));

        // 3. A MÁGICA: Atualizar o valor do sonho
        dream.addContribution(contribution.getAmount());

        // 4. Salvar o sonho atualizado (O JPA entende o 'dirty checking', mas salvar é mais seguro)
        dreamRepository.save(dream);

        // 5. Salvar a contribuição
        return contributionRepository.save(contribution);
    }

    public List<ContributionResponseDTO> findByDream(Long dreamId) {
    return contributionRepository.findByDreamId(dreamId)
            .stream()
            .map(ContributionResponseDTO::new)
            .toList();
}
    public List<UserRankingDTO> getRanking() {
    return contributionRepository.getTopDonors();
}
}
