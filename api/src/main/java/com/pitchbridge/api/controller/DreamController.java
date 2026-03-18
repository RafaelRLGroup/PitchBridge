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
        List<DreamResponseDTO> dtos = dreamService.findAll()
                .stream()
                .map(DreamResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<DreamResponseDTO> createDream(@RequestBody Dream dream) {
        Dream savedDream = dreamService.save(dream);
        return ResponseEntity.ok(new DreamResponseDTO(savedDream));
    }
}
