package com.pitchbridge.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class UserRankingDTO {
    private String name;
    private BigDecimal totalDonated;
}
