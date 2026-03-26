package com.pitchbridge.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class) // <-- Adicione isso aqui!
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dream {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate // Carimba a data de criação automaticamente
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private String title;
    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal goalValue; // O objetivo final (Ex: 50.000)

    // O "cofrinho" do sonho: começa com zero!
    @Column(precision = 10, scale = 2)
    private BigDecimal currentAmount = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;

    @OneToMany(mappedBy = "dream")
    private List<Contribution> contributions = new ArrayList<>();

    // Método auxiliar para facilitar a vida do Service
    public void addContribution(BigDecimal amount) {
        if (this.currentAmount == null) this.currentAmount = BigDecimal.ZERO;
        this.currentAmount = this.currentAmount.add(amount);
    }
}
