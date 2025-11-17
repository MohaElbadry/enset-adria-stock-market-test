package org.example.companyservice.dto;

import java.time.LocalDate;

public record CompanyResponseDTO(
        Long id,
        String name,
        LocalDate listingDate,
        Double currentStockPrice,
        String sector
) {}
