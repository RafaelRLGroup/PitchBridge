package com.pitchbridge.api.dto;

import com.pitchbridge.api.model.Contribution;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class ContributionResponseDTO {
    private Long id;
    private BigDecimal amount;
    private String donorName;

    public ContributionResponseDTO(Contribution entity) {
        this.id = entity.getId();
        this.amount = entity.getAmount();

        // A REGRA DE OURO:
        if (entity.isAnonymous()) {
            this.donorName = "Investidor Anônimo";
        } else {
            this.donorName = entity.getDonor().getName();
        }
    }
}
