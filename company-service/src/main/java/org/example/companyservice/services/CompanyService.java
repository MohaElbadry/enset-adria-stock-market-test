package org.example.companyservice.services;

import java.util.List;

import org.example.companyservice.dto.CompanyRequestDTO;
import org.example.companyservice.dto.CompanyResponseDTO;

public interface CompanyService {
    CompanyResponseDTO create(CompanyRequestDTO request);
    List<CompanyResponseDTO> findAll();
    CompanyResponseDTO findById(Long id);
    CompanyResponseDTO updatePrice(Long id, Double price);
    void delete(Long id);
    List<CompanyResponseDTO> findBySector(String sector);
}
