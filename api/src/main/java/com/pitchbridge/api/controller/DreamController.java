package com.pitchbridge.api.controller;

import com.pitchbridge.api.dto.DreamResponseDTO;
import com.pitchbridge.api.model.Dream;
import com.pitchbridge.api.service.DreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dreams")
public class DreamController {

    @Autowired
    private DreamService dreamService;

    @GetMapping
    public ResponseEntity<List<DreamResponseDTO>> getAllDreams() {
        // O Service já entrega a lista de DTOs prontinha!
        List<DreamResponseDTO> dtos = dreamService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<DreamResponseDTO> createDream(@RequestBody Dream dream) {
        // O Service agora já devolve o DTO prontinho!
        return ResponseEntity.ok(dreamService.save(dream));
}
    @GetMapping("/search")
    public ResponseEntity<List<DreamResponseDTO>> search(@RequestParam String title) {
        return ResponseEntity.ok(dreamService.searchByTitle(title));
    }

    @GetMapping("/trending")
    public ResponseEntity<List<DreamResponseDTO>> getTrending() {
        return ResponseEntity.ok(dreamService.getTrendingDreams());
    }
}
