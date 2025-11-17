package org.example.stockservice.web;

import java.util.List;

import org.example.stockservice.dto.StockRequestDTO;
import org.example.stockservice.dto.StockResponseDTO;
import org.example.stockservice.services.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
public class StockRestController {

    private final StockService service;

    public StockRestController(StockService service) {
        this.service = service;
    }

    // Ajouter une cotation
    @PostMapping
    public ResponseEntity<StockResponseDTO> create(@RequestBody StockRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    // Consulter toutes les cotations
    @GetMapping
    public List<StockResponseDTO> findAll() {
        return service.findAll();
    }

    // Consulter une cotation by id
    @GetMapping("/{id}")
    public StockResponseDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    // Supprimer une cotation
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // Cotations par entreprise
    @GetMapping("/company/{companyId}")
    public List<StockResponseDTO> findByCompany(@PathVariable Long companyId) {
        return service.findByCompany(companyId);
    }
}
