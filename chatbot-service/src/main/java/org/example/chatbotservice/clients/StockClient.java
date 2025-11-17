package org.example.chatbotservice.clients;

import org.example.chatbotservice.dto.StockRequest;
import org.example.chatbotservice.dto.StockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "stock-service")
public interface StockClient {

    @PostMapping("/api/stocks")
    StockResponse create(@RequestBody StockRequest request);

    @GetMapping("/api/stocks")
    List<StockResponse> findAll();

    @GetMapping("/api/stocks/{id}")
    StockResponse findById(@PathVariable("id") Long id);

    @DeleteMapping("/api/stocks/{id}")
    void delete(@PathVariable("id") Long id);

    @GetMapping("/api/stocks/company/{companyId}")
    List<StockResponse> findByCompany(@PathVariable("companyId") Long companyId);

    @PutMapping("/api/stocks/company/{companyId}/update-company-price")
    void updateCompanyPrice(@PathVariable("companyId") Long companyId);
}
