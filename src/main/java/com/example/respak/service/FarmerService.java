package com.example.respak.service;

import com.example.dto.FarmerDto;
import com.example.dto.farmerDtoWithoutArchive;
import com.example.respak.model.District;
import com.example.respak.model.Farmer;
import com.example.respak.repository.DistrictRepository;
import com.example.respak.repository.FarmerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmerService {

    private final FarmerRepository farmerRepository;
    private final DistrictRepository districtRepository;

    public FarmerService(FarmerRepository farmerRepository, DistrictRepository districtRepository) {
        this.farmerRepository = farmerRepository;
        this.districtRepository = districtRepository;
    }

    // Получение списка фермеров с фильтрацией
    public List<farmerDtoWithoutArchive> getFarmers(String organizationName, Farmer.LegalForm legalForm, String inn,
            Long districtId,
            LocalDate startDate, LocalDate endDate, boolean archived) {

        List<Farmer> farmers = farmerRepository.findByArchivedFalse();

        if (organizationName != null) {
            farmers = farmerRepository.findByOrganizationNameContainingIgnoreCaseAndArchivedFalse(organizationName);
        } else if (legalForm != null) {
            farmers = farmerRepository.findByLegalFormAndArchivedFalse(legalForm);
        } else if (inn != null) {
            farmers = farmerRepository.findByInnAndArchivedFalse(inn);
        } else if (districtId != null) {
            District district = districtRepository.findById(districtId)
                    .orElseThrow(() -> new RuntimeException("Район не найден"));
            farmers = farmerRepository.findByRegistrationDistrictAndArchivedFalse(district);
        } else if (startDate != null && endDate != null) {
            farmers = farmerRepository.findByRegistrationDateBetweenAndArchivedFalse(startDate, endDate);
        } else if (archived) {
            farmers = farmerRepository.findByArchivedTrue();
        }
        return farmers.stream()
                .map(d -> new farmerDtoWithoutArchive(d))
                .collect(Collectors.toList());
    }

    // Добавление нового фермера
    public Farmer addFarmer(FarmerDto farmer) {
        if (farmerRepository.findByInn(farmer.getInn()).isEmpty()) {
            return saveFarmer(farmer);
        } else {
            throw new IllegalArgumentException("Фермер с таким ИНН уже существует");
        }
    }

    public Farmer saveFarmer(FarmerDto farmerDto) {
        Farmer farmer = new Farmer();
        farmer.setOrganizationName(farmerDto.getOrganizationName());
        farmer.setLegalForm(Farmer.LegalForm.valueOf(farmerDto.getLegalForm()));
        farmer.setInn(farmerDto.getInn());
        farmer.setKpp(farmerDto.getKpp());
        farmer.setOgrn(farmerDto.getOgrn());
        farmer.setRegistrationDate(farmerDto.getRegistrationDate());
        farmer.setArchived(farmerDto.isArchived());

        // Найти район регистрации по ID
        District registrationDistrict = districtRepository.findById(farmerDto.getRegistrationDistrictId())
                .orElseThrow(() -> new IllegalArgumentException("District not found"));
        farmer.setRegistrationDistrict(registrationDistrict);

        // Найти районы посевных полей по их ID
        List<District> sowingDistricts = districtRepository.findAllById(farmerDto.getSowingDistrictIds());
        farmer.setSowingDistricts(sowingDistricts);

        return farmerRepository.save(farmer);
    }

    // Архивирование фермера
    public Farmer archiveFarmer(Long id) {
        Farmer farmer = farmerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Фермер не найден"));
        farmer.setArchived(true);
        return farmerRepository.save(farmer);
    }
}
