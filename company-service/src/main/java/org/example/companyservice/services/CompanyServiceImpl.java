package org.example.companyservice.services;

import java.util.List;

import org.example.companyservice.dto.CompanyRequestDTO;
import org.example.companyservice.dto.CompanyResponseDTO;
import org.example.companyservice.entities.Company;
import org.example.companyservice.mappers.CompanyMapper;
import org.example.companyservice.repositories.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;

    public CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompanyResponseDTO create(CompanyRequestDTO request) {
        Company entity = CompanyMapper.toEntity(request);
        Company saved = repository.save(entity);
        return CompanyMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponseDTO> findAll() {
        return repository.findAll().stream().map(CompanyMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponseDTO findById(Long id) {
        return repository.findById(id).map(CompanyMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Company not found: " + id));
    }

    @Override
    public CompanyResponseDTO updatePrice(Long id, Double price) {
        Company company = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Company not found: " + id));
        company.setCurrentStockPrice(price);
        return CompanyMapper.toResponse(repository.save(company));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponseDTO> findBySector(String sector) {
        return repository.findBySectorIgnoreCase(sector).stream().map(CompanyMapper::toResponse).toList();
    }
}
