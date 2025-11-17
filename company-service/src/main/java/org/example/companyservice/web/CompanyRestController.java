package org.example.companyservice.web;

import java.util.List;

import org.example.companyservice.dto.CompanyRequestDTO;
import org.example.companyservice.dto.CompanyResponseDTO;
import org.example.companyservice.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/companies")
public class CompanyRestController {

    private final CompanyService service;

    public CompanyRestController(CompanyService service) {
        this.service = service;
    }

    // Enregistrer une entreprise
    @PostMapping
    public ResponseEntity<CompanyResponseDTO> create(@RequestBody CompanyRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    // Consulter toutes les entreprises
    @GetMapping
    public List<CompanyResponseDTO> findAll() {
        return service.findAll();
    }

    // Consulter une entreprise par son id
    @GetMapping("/{id}")
    public CompanyResponseDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    // Mettre à jour le prix actuel de l’action
    @PutMapping("/{id}/price")
    public CompanyResponseDTO updatePrice(@PathVariable Long id, @RequestParam("value") Double price) {
        return service.updatePrice(id, price);
    }

    // Supprimer une entreprise
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // Consulter les entreprises d’un domaine (secteur)
    @GetMapping("/sector/{sector}")
    public List<CompanyResponseDTO> bySector(@PathVariable String sector) {
        return service.findBySector(sector);
    }
}
