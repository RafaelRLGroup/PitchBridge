package com.pitchbridge.api.dto;

import com.pitchbridge.api.model.Dream;
import lombok.Getter;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class DreamResponseDTO {
    private Long id;
    private String title;
    private String description;
    private BigDecimal goalValue;
    private BigDecimal currentAmount;
    private String creatorName;

    // Novos campos "Inteligentes"
    private Integer contributionCount;
    private BigDecimal percentageReached;

    public DreamResponseDTO(Dream dream) {
        this.id = dream.getId();
        this.title = dream.getTitle();
        this.description = dream.getDescription();
        this.goalValue = dream.getGoalValue();
        this.currentAmount = dream.getCurrentAmount();
        this.creatorName = dream.getCreator() != null ? dream.getCreator().getName() : "Anônimo";

        // 1. Pega a quantidade de contribuições da lista da entidade
        // (Certifique-se que você tem a List<Contribution> na sua entidade Dream)
        this.contributionCount = (dream.getContributions() != null) ? dream.getContributions().size() : 0;

        // 2. Cálculo da Porcentagem: (Atual / Meta) * 100
        if (dream.getGoalValue() != null && dream.getGoalValue().compareTo(BigDecimal.ZERO) > 0) {
            this.percentageReached = dream.getCurrentAmount()
                    .multiply(new BigDecimal("100"))
                    .divide(dream.getGoalValue(), 2, RoundingMode.HALF_UP);
        } else {
            this.percentageReached = BigDecimal.ZERO;
        }
    }
}
