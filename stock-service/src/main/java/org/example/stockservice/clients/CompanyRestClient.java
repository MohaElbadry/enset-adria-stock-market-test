package org.example.stockservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "company-service")
public interface CompanyRestClient {

    @PutMapping("/api/companies/{id}/price")
    void updateCompanyPrice(@PathVariable("id") Long companyId,
                            @RequestParam("value") Double price);
}
