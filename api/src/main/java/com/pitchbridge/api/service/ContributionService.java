package com.pitchbridge.api.service;

import com.pitchbridge.api.dto.ContributionResponseDTO;
import com.pitchbridge.api.dto.UserRankingDTO;
import com.pitchbridge.api.model.Contribution;
import com.pitchbridge.api.model.Dream;
import com.pitchbridge.api.model.User;
import com.pitchbridge.api.repository.ContributionRepository;
import com.pitchbridge.api.repository.DreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
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
    public ContributionResponseDTO createContribution(Contribution contribution) {
        // 1. Validar e carregar o Doador completo
        User donor = userService.findById(contribution.getDonor().getId());
        contribution.setDonor(donor);

        // 2. Validar e carregar o Sonho
        Dream dream = dreamRepository.findById(contribution.getDream().getId())
                .orElseThrow(() -> new RuntimeException("Sonho não encontrado, meu nobre!"));

        // 3. Atualizar o saldo do sonho usando o método interno da entidade
        dream.addContribution(contribution.getAmount());

        // 4. Configurar carimbo de data/hora
        contribution.setCreatedAt(Instant.now());

        // 5. Salvar a contribuição (O Dream será salvo automaticamente pelo @Transactional)
        Contribution saved = contributionRepository.save(contribution);

        // 6. Retornar o DTO para o controller
        return new ContributionResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public Page<ContributionResponseDTO> findByDream(Long dreamId, Pageable pageable) {
        // Busca paginada para o Mural de Gratidão
        return contributionRepository.findByDreamId(dreamId, pageable)
                .map(ContributionResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public List<UserRankingDTO> getRanking() {
        return contributionRepository.getTopDonors();
    }
}
