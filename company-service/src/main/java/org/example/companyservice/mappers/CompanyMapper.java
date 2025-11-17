package org.example.companyservice.mappers;

import org.example.companyservice.dto.CompanyRequestDTO;
import org.example.companyservice.dto.CompanyResponseDTO;
import org.example.companyservice.entities.Company;

public class CompanyMapper {
    public static Company toEntity(CompanyRequestDTO dto) {
        if (dto == null) return null;
        return Company.builder()
                .name(dto.name())
                .listingDate(dto.listingDate())
                .currentStockPrice(dto.currentStockPrice())
                .sector(dto.sector())
                .build();
    }

    public static CompanyResponseDTO toResponse(Company company) {
        if (company == null) return null;
        return new CompanyResponseDTO(
                company.getId(),
                company.getName(),
                company.getListingDate(),
                company.getCurrentStockPrice(),
                company.getSector()
        );
    }
}
