package com.pitchbridge.api.dto;

import com.pitchbridge.api.model.Dream;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class DreamResponseDTO {
    private Long id;
    private String title;
    private String description;
    private BigDecimal goalValue;
    private BigDecimal currentAmount;
    private String creatorName; // Em vez do objeto User inteiro, passamos só o nome

    public DreamResponseDTO(Dream dream) {
        this.id = dream.getId();
        this.title = dream.getTitle();
        this.description = dream.getDescription();
        this.goalValue = dream.getGoalValue();
        this.currentAmount = dream.getCurrentAmount();
        // Aqui a gente "achata" a relação, pegando só o necessário
        this.creatorName = dream.getCreator() != null ? dream.getCreator().getName() : "Anônimo";
    }
}
