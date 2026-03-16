package com.pitchbridge.api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class Dream {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private BigDecimal goalValue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;
}
