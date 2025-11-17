package org.example.stockservice;

import java.time.LocalDateTime;
import java.util.List;

import org.example.stockservice.entities.StockMarket;
import org.example.stockservice.repositories.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients(basePackages = "org.example.stockservice.clients")
public class StockServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockServiceApplication.class, args);
    }

    // Seed initial stock quotations for development
    @Bean
    CommandLineRunner stockSeedRunner(StockRepository stockRepository) {
        return args -> {
            if (stockRepository.count() == 0) {
                LocalDateTime base = LocalDateTime.now().minusDays(3);
                List<StockMarket> seed = List.of(
                        // Company 1
                        StockMarket.builder().companyId(1L).date(base)
                                .openValue(100.0).highValue(110.0).lowValue(95.0).closeValue(108.0)
                                .volume(3_500L).build(),
                        StockMarket.builder().companyId(1L).date(base.plusDays(1))
                                .openValue(108.0).highValue(120.0).lowValue(105.0).closeValue(115.0)
                                .volume(5_000L).build(),
                        // Company 2
                        StockMarket.builder().companyId(2L).date(base.plusDays(2))
                                .openValue(70.0).highValue(82.0).lowValue(68.0).closeValue(79.5)
                                .volume(4_200L).build()
                );
                stockRepository.saveAll(seed);
            }

            stockRepository.findAll().forEach(s -> {
                System.out.println("-----------");
                System.out.println("ID: " + s.getId());
                System.out.println("Company: " + s.getCompanyId());
                System.out.println("Date: " + s.getDate());
                System.out.println("O/H/L/C: " + s.getOpenValue() + "/" + s.getHighValue() + "/" + s.getLowValue() + "/" + s.getCloseValue());
                System.out.println("Volume: " + s.getVolume());
                System.out.println("-----------");
            });
        };
    }
}
