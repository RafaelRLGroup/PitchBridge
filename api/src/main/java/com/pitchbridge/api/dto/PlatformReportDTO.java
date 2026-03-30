package com.pitchbridge.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PlatformReportDTO {
    private Long totalDreams;
    private Long totalContributions;
    private BigDecimal totalArrecadado;
}
