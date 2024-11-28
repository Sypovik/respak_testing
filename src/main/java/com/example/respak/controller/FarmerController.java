package com.example.respak.controller;

import com.example.dto.FarmerDto;
import com.example.dto.farmerDtoWithoutArchive;
import com.example.respak.model.Farmer;
import com.example.respak.service.FarmerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    // Получение списка фермеров с фильтрацией
    @GetMapping
    public ResponseEntity<List<farmerDtoWithoutArchive>> getFarmers(
            @RequestParam(required = false) String organizationName,
            @RequestParam(required = false) Farmer.LegalForm legalForm,
            @RequestParam(required = false) String inn,
            @RequestParam(required = false) Long districtId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {
        List<farmerDtoWithoutArchive> farmers = farmerService.getFarmers(organizationName, legalForm, inn, districtId,
                startDate,
                endDate);
        return ResponseEntity.ok(farmers);
    }

    // Добавление нового фермера
    @PostMapping
    public ResponseEntity<Farmer> addFarmer(@RequestBody FarmerDto farmer) {
        Farmer createdFarmer = farmerService.addFarmer(farmer);
        return ResponseEntity.ok(createdFarmer);
    }

    // Архивирование фермера
    @PutMapping("/{id}/archive")
    public ResponseEntity<Farmer> archiveFarmer(@PathVariable Long id) {
        Farmer archivedFarmer = farmerService.archiveFarmer(id);
        return ResponseEntity.ok(archivedFarmer);
    }
}
