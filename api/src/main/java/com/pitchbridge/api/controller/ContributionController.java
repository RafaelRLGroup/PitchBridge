package com.pitchbridge.api.controller;

import com.pitchbridge.api.dto.ContributionResponseDTO;
import com.pitchbridge.api.dto.UserRankingDTO;
import com.pitchbridge.api.model.Contribution;
import com.pitchbridge.api.service.ContributionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page; // Importante para o Mural
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contributions")
public class ContributionController {

    @Autowired
    private ContributionService contributionService;

    @PostMapping
    public ResponseEntity<ContributionResponseDTO> postContribution(@RequestBody Contribution contribution) {
        // O Service agora retorna o DTO prontinho e atualiza o saldo do sonho
        ContributionResponseDTO saved = contributionService.createContribution(contribution);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Mural de Gratidão via rota de contribuições (opcional, já temos no DreamController)
    @GetMapping("/dream/{dreamId}")
    public ResponseEntity<Page<ContributionResponseDTO>> getByDream(
            @PathVariable Long dreamId,
            @PageableDefault(size = 10, sort = "donatedAt", direction = Sort.Direction.DESC) Pageable pageable) {

        return ResponseEntity.ok(contributionService.findByDream(dreamId, pageable));
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<UserRankingDTO>> getRanking() {
        // O ranking continua sendo uma lista simples (Top 10)
        return ResponseEntity.ok(contributionService.getRanking());
    }
}
