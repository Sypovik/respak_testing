package com.example.respak.controller;

import com.example.respak.model.District;
import com.example.respak.service.DistrictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    // Получение списка районов с фильтрацией
    @GetMapping
    public ResponseEntity<List<District>> getDistricts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String code) {
        List<District> districts = districtService.getDistricts(name, code);
        return ResponseEntity.ok(districts);
    }

    // Добавление нового района
    @PostMapping
    public ResponseEntity<District> addDistrict(@RequestBody District district) {
        District createdDistrict = districtService.addDistrict(district);
        return ResponseEntity.ok(createdDistrict);
    }

    // Архивирование района
    @PutMapping("/{id}/archive")
    public ResponseEntity<District> archiveDistrict(@PathVariable Long id) {
        District archivedDistrict = districtService.archiveDistrict(id);
        return ResponseEntity.ok(archivedDistrict);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDistrict(@PathVariable Long id) {
        try {
            districtService.deleteDistrict(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
