package com.pitchbridge.api.dto;

import com.pitchbridge.api.model.Dream;
import lombok.Getter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

@Getter
public class DreamResponseDTO {
    private Long id;
    private String title;
    private String description;
    private BigDecimal goalValue;
    private BigDecimal currentAmount;
    private String creatorName;
    private Integer contributionCount;
    private BigDecimal percentageReached;
    private Instant createdAt;

    public DreamResponseDTO(Dream dream) {
        // 1. Atribuições Básicas
        this.id = dream.getId();
        this.title = dream.getTitle();
        this.description = dream.getDescription();
        this.goalValue = dream.getGoalValue();
        this.createdAt = dream.getCreatedAt();

        // 2. O PULO DO GATO: Tratamento de NULL
        // Se o sonho é novo e o banco devolver null no currentAmount,
        // a gente usa BigDecimal.ZERO para não quebrar a conta abaixo.
        this.currentAmount = (dream.getCurrentAmount() != null) ? dream.getCurrentAmount() : BigDecimal.ZERO;

        // 3. Nome do Criador
        this.creatorName = (dream.getCreator() != null) ? dream.getCreator().getName() : "Anônimo";

        // 4. Contagem de doações (Tamanho da lista)
        this.contributionCount = (dream.getContributions() != null) ? dream.getContributions().size() : 0;

        // 5. CÁLCULO DA PORCENTAGEM
        // Usamos o 'this.currentAmount' que já garantimos lá em cima que não é nulo.
        if (this.goalValue != null && this.goalValue.compareTo(BigDecimal.ZERO) > 0) {
            this.percentageReached = this.currentAmount
                    .multiply(new BigDecimal("100"))
                    .divide(this.goalValue, 2, RoundingMode.HALF_UP);
        } else {
            this.percentageReached = BigDecimal.ZERO;
        }
    }
}
