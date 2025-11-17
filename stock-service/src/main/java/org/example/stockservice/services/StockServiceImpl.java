package org.example.stockservice.services;

import java.util.List;

import org.example.stockservice.clients.CompanyRestClient;
import org.example.stockservice.dto.StockRequestDTO;
import org.example.stockservice.dto.StockResponseDTO;
import org.example.stockservice.entities.StockMarket;
import org.example.stockservice.mappers.StockMapper;
import org.example.stockservice.repositories.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
@Transactional
public class StockServiceImpl implements StockService {

    private final StockRepository repository;
    private final CompanyRestClient companyClient;

    public StockServiceImpl(StockRepository repository, CompanyRestClient companyClient) {
        this.repository = repository;
        this.companyClient = companyClient;
    }

    @Override
    public StockResponseDTO create(StockRequestDTO request) {
        StockMarket entity = StockMapper.toEntity(request);
        StockMarket saved = repository.save(entity);

        // After saving, compute latest closeValue and update company price
        repository.findTopByCompanyIdOrderByDateDesc(saved.getCompanyId())
                .ifPresent(latest -> updateCompanyPriceSafe(latest.getCompanyId(), latest.getCloseValue()));

        return StockMapper.toResponse(saved);
    }

    // Wrap company price update with a circuit breaker so stock creation still succeeds
    @CircuitBreaker(name = "companyService", fallbackMethod = "updateCompanyPriceFallback")
    public void updateCompanyPriceSafe(Long companyId, Double price) {
        if (companyId != null && price != null) {
            companyClient.updateCompanyPrice(companyId, price);
        }
    }

    // Fallback method signature must match original with Throwable at the end
    public void updateCompanyPriceFallback(Long companyId, Double price, Throwable t) {
        // We can log the failure; in a real app, store an outbox event for later retry
        System.err.printf("[CircuitBreaker] Failed to update company %d price to %f: %s%n",
                companyId, price, t.getMessage());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockResponseDTO> findAll() {
        return repository.findAll().stream().map(StockMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StockResponseDTO findById(Long id) {
        return repository.findById(id).map(StockMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Stock not found: " + id));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockResponseDTO> findByCompany(Long companyId) {
        return repository.findByCompanyId(companyId).stream().map(StockMapper::toResponse).toList();
    }

    @Override
    public void updateCompanyCurrentPrice(Long companyId) {
        repository.findTopByCompanyIdOrderByDateDesc(companyId)
                .ifPresent(latest -> updateCompanyPriceSafe(companyId, latest.getCloseValue()));
    }
}
