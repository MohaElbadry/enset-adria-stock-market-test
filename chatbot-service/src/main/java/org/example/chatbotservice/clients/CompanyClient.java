package org.example.chatbotservice.clients;

import org.example.chatbotservice.dto.CompanyRequest;
import org.example.chatbotservice.dto.CompanyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "company-service")
public interface CompanyClient {

    @GetMapping("/api/companies")
    List<CompanyResponse> findAll();

    @GetMapping("/api/companies/{id}")
    CompanyResponse findById(@PathVariable("id") Long id);

    @GetMapping("/api/companies/sector/{sector}")
    List<CompanyResponse> findBySector(@PathVariable("sector") String sector);

    @PostMapping("/api/companies")
    CompanyResponse create(@RequestBody CompanyRequest request);

    @PutMapping("/api/companies/{id}/price")
    CompanyResponse updatePrice(@PathVariable("id") Long id,
                                @RequestParam("value") Double value);
}
