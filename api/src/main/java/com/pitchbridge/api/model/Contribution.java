package com.pitchbridge.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull; // <-- Faltava esse cara!
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Contribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O valor da doação é obrigatório!")
    @DecimalMin(value = "1.00", message = "A doação mínima é de R$ 1,00, vamos ajudar né?")
    private BigDecimal amount;

    private boolean anonymous;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User donor;

    @ManyToOne
    @JoinColumn(name = "dream_id")
    private Dream dream; // <-- Trocamos 'User' por 'Dream' de vez!

    // Importante: Importar de jakarta.persistence
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
}
