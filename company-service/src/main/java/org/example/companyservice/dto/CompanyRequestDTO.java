package org.example.companyservice.dto;

import java.time.LocalDate;

public record CompanyRequestDTO(
        String name,
        LocalDate listingDate,
        Double currentStockPrice,
        String sector
) {}
