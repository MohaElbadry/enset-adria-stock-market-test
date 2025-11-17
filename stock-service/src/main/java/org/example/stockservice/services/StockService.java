package org.example.stockservice.services;

import java.util.List;

import org.example.stockservice.dto.StockRequestDTO;
import org.example.stockservice.dto.StockResponseDTO;

public interface StockService {
    StockResponseDTO create(StockRequestDTO request);
    List<StockResponseDTO> findAll();
    StockResponseDTO findById(Long id);
    void delete(Long id);
    List<StockResponseDTO> findByCompany(Long companyId);
}
