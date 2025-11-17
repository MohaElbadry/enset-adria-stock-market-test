package org.example.stockservice.repositories;

import org.example.stockservice.entities.StockMarket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<StockMarket, Long> {
    List<StockMarket> findByCompanyId(Long companyId);
    Optional<StockMarket> findTopByCompanyIdOrderByDateDesc(Long companyId);
}
