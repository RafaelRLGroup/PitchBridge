package com.pitchbridge.api.service;

import com.pitchbridge.api.model.Dream;
import com.pitchbridge.api.repository.DreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DreamService {

    @Autowired
    private DreamRepository dreamRepository;

    public List<Dream> findAll() {
        return dreamRepository.findAll();
    }

    public Dream save(Dream dream) {
        return dreamRepository.save(dream);
    }

    public Dream findById(Long id) {
        return dreamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sonho não encontrado!"));
    }
}
