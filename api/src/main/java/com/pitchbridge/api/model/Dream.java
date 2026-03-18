package com.pitchbridge.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dream {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    // Método auxiliar para facilitar a vida do Service
    public void addContribution(BigDecimal amount) {
        if (this.currentAmount == null) this.currentAmount = BigDecimal.ZERO;
        this.currentAmount = this.currentAmount.add(amount);
    }
}
