package org.example.companyservice.repositories;

import java.util.List;

import org.example.companyservice.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findBySectorIgnoreCase(String sector);
}
