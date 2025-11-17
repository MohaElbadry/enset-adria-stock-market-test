package org.example.chatbotservice.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockResponse {
    private Long id;
    private LocalDateTime date;
    private Double openValue;
    private Double highValue;
    private Double lowValue;
    private Double closeValue;
    private Long volume;
    private Long companyId;
}
