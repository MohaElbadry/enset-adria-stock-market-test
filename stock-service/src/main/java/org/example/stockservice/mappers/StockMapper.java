package org.example.stockservice.mappers;

import org.example.stockservice.dto.StockRequestDTO;
import org.example.stockservice.dto.StockResponseDTO;
import org.example.stockservice.entities.StockMarket;

public class StockMapper {
    public static StockMarket toEntity(StockRequestDTO dto) {
        if (dto == null) return null;
        return StockMarket.builder()
                .date(dto.date())
                .openValue(dto.openValue())
                .highValue(dto.highValue())
                .lowValue(dto.lowValue())
                .closeValue(dto.closeValue())
                .volume(dto.volume())
                .companyId(dto.companyId())
                .build();
    }

    public static StockResponseDTO toResponse(StockMarket s) {
        if (s == null) return null;
        return new StockResponseDTO(
                s.getId(),
                s.getDate(),
                s.getOpenValue(),
                s.getHighValue(),
                s.getLowValue(),
                s.getCloseValue(),
                s.getVolume(),
                s.getCompanyId()
        );
    }
}
