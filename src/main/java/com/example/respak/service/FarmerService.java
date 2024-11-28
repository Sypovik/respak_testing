package com.example.respak.service;

import com.example.respak.model.District;
import com.example.respak.model.Farmer;
import com.example.respak.repository.DistrictRepository;
import com.example.respak.repository.FarmerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FarmerService {

    private final FarmerRepository farmerRepository;
    private final DistrictRepository districtRepository;

    public FarmerService(FarmerRepository farmerRepository, DistrictRepository districtRepository) {
        this.farmerRepository = farmerRepository;
        this.districtRepository = districtRepository;
    }

    // Получение списка фермеров с фильтрацией
    public List<Farmer> getFarmers(String organizationName, Farmer.LegalForm legalForm, String inn, Long districtId,
            LocalDate startDate, LocalDate endDate) {
        if (organizationName != null) {
            return farmerRepository.findByOrganizationNameContainingIgnoreCase(organizationName);
        } else if (legalForm != null) {
            return farmerRepository.findByLegalForm(legalForm);
        } else if (inn != null) {
            return farmerRepository.findByInn(inn);
        } else if (districtId != null) {
            District district = districtRepository.findById(districtId)
                    .orElseThrow(() -> new RuntimeException("Район не найден"));
            return farmerRepository.findByRegistrationDistrict(district);
        } else if (startDate != null && endDate != null) {
            return farmerRepository.findByRegistrationDateBetween(startDate, endDate);
        }
        return farmerRepository.findByArchivedFalse();
    }

    // Добавление нового фермера
    public Farmer addFarmer(Farmer farmer) {
        if (farmerRepository.findByInn(farmer.getInn()).isEmpty()) {
            return farmerRepository.save(farmer);
        } else {
            throw new IllegalArgumentException("Фермер с таким ИНН уже существует");
        }
    }

    // Архивирование фермера
    public Farmer archiveFarmer(Long id) {
        Farmer farmer = farmerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Фермер не найден"));
        farmer.setArchived(true);
        return farmerRepository.save(farmer);
    }
}
