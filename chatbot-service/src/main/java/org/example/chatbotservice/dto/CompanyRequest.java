package org.example.chatbotservice.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequest {
        private String name;
        private LocalDate listingDate;
        private Double currentStockPrice;
        private String sector;
}
