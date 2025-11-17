package org.example.stockservice.dto;

import java.time.LocalDateTime;

public record StockRequestDTO(
        LocalDateTime date,
        Double openValue,
        Double highValue,
        Double lowValue,
        Double closeValue,
        Long volume,
        Long companyId
) {}
