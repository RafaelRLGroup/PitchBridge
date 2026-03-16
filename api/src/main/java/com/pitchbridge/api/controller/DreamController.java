package com.pitchbridge.api.controller;

import com.pitchbridge.api.model.Dream;
import com.pitchbridge.api.repository.DreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dreams")
public class DreamController {

    @Autowired
    private DreamRepository dreamRepository;

    @GetMapping
    public List<Dream> getAllDreams() {
        return dreamRepository.findAll();
    }

    @PostMapping
    public Dream createDream(@RequestBody Dream dream) {
        // Por enquanto, vamos passar o ID do usuário direto no JSON
        return dreamRepository.save(dream);
    }
}
