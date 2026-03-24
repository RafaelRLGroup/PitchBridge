package com.pitchbridge.api.controller;

import com.pitchbridge.api.dto.ContributionResponseDTO;
import com.pitchbridge.api.model.Contribution;
import com.pitchbridge.api.service.ContributionService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Contribution> postContribution(@RequestBody Contribution contribution) {
        // O Service vai fazer a mágica de validar e somar no sonho
        Contribution saved = contributionService.createContribution(contribution);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/dream/{dreamId}")
    public ResponseEntity<List<ContributionResponseDTO>> getByDream(@PathVariable Long dreamId) {
    return ResponseEntity.ok(contributionService.findByDream(dreamId));
}
}
