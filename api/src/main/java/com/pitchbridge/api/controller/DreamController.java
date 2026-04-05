package com.pitchbridge.api.controller;

import com.pitchbridge.api.dto.ContributionResponseDTO;
import com.pitchbridge.api.dto.DreamResponseDTO;
import com.pitchbridge.api.dto.PlatformReportDTO;
import com.pitchbridge.api.model.Dream;
import com.pitchbridge.api.service.ContributionService;
import com.pitchbridge.api.service.DreamService;
import com.pitchbridge.api.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dreams")
public class DreamController {

    @Autowired
    private DreamService dreamService;

    @Autowired // <-- FIX 1: OBRIGATÓRIO PARA O SERVIÇO FUNCIONAR
    private ContributionService contributionService;

    @GetMapping
    public ResponseEntity<Page<DreamResponseDTO>> getAllDreams(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(dreamService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<DreamResponseDTO> createDream(@RequestBody Dream dream) {
        return ResponseEntity.ok(dreamService.save(dream));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<DreamResponseDTO>> search(
            @RequestParam String title,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(dreamService.searchByTitle(title, pageable));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Page<DreamResponseDTO>> getByCategory(
            @PathVariable Category category,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(dreamService.findByCategory(category, pageable));
    }

    @GetMapping("/trending")
    public ResponseEntity<List<DreamResponseDTO>> getTrending() {
        return ResponseEntity.ok(dreamService.getTrendingDreams());
    }

    @GetMapping("/stats")
    public ResponseEntity<PlatformReportDTO> getStats() {
        return ResponseEntity.ok(dreamService.getPlatformReport());
    }

    // Mural de Gratidão Paginado
    @GetMapping("/{id}/contributions")
    public ResponseEntity<Page<ContributionResponseDTO>> getDreamMural(
            @PathVariable Long id,
            @PageableDefault(size = 5, sort = "donatedAt", direction = Sort.Direction.DESC) Pageable pageable) {

        // FIX 2: Adicionado 'return' e garantido o nome do método que criamos no Service
        return ResponseEntity.ok(contributionService.findByDream(id, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DreamResponseDTO> getById(@PathVariable Long id) {
        // Chamamos o findById do service e convertemos para DTO
        Dream dream = dreamService.findById(id);
        return ResponseEntity.ok(new DreamResponseDTO(dream));
    }

}
