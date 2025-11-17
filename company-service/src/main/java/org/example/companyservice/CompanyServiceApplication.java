package org.example.companyservice;

import org.example.companyservice.entities.Company;
import org.example.companyservice.repositories.CompanyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class CompanyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyServiceApplication.class, args);
    }

    // Seed initial data in H2 for development
    @Bean
    CommandLineRunner commandLineRunner(CompanyRepository companyRepository) {
        return args -> {
            if (companyRepository.count() == 0) {
                List<Company> seed = List.of(
                        Company.builder()
                                .name("AdriaTech")
                                .listingDate(LocalDate.of(2020, 5, 12))
                                .currentStockPrice(125.50)
                                .sector("IT")
                                .build(),
                        Company.builder()
                                .name("NeoBank")
                                .listingDate(LocalDate.of(2019, 3, 3))
                                .currentStockPrice(78.20)
                                .sector("Banque")
                                .build(),
                        Company.builder()
                                .name("AssurPlus")
                                .listingDate(LocalDate.of(2021, 10, 1))
                                .currentStockPrice(43.10)
                                .sector("Assurance")
                                .build()
                );
                companyRepository.saveAll(seed);
            }

            companyRepository.findAll().forEach(c -> {
                System.out.println("-----------");
                System.out.println("ID: " + c.getId());
                System.out.println("Name: " + c.getName());
                System.out.println("Sector: " + c.getSector());
                System.out.println("ListingDate: " + c.getListingDate());
                System.out.println("CurrentPrice: " + c.getCurrentStockPrice());
                System.out.println("-----------");
            });
        };
    }
}
