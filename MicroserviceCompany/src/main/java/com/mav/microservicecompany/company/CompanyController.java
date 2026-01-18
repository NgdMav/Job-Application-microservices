package com.mav.microservicecompany.company;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Company>> getAll() {
        return ResponseEntity.ok(companyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getOneById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getOneById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Company> create(@Valid @RequestBody Company company) {
        return ResponseEntity.ok(companyService.create(company));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> update(@PathVariable Long id, @Valid @RequestBody Company company) {
        return ResponseEntity.ok(companyService.update(id, company));
    }
}
