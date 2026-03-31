package com.pitchbridge.api.dto;

import com.pitchbridge.api.model.Dream;
import lombok.Getter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import com.pitchbridge.api.model.Category;

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
    private Category category;

    public DreamResponseDTO(Dream dream) {
        // 1. Atribuições Básicas
        this.id = dream.getId();
        this.title = dream.getTitle();
        this.description = dream.getDescription();
        this.goalValue = dream.getGoalValue();
        this.createdAt = dream.getCreatedAt();

        // 2. ATRIBUIÇÃO DA CATEGORIA
        // Adicionamos um tratamento simples para não vir nulo caso o banco esteja antigo
        this.category = (dream.getCategory() != null) ? dream.getCategory() : Category.OUTROS;
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
