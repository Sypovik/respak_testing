package com.example.respak.service;

import com.example.dto.DistrictDtoWithoutArchive;
import com.example.respak.model.District;
import com.example.respak.repository.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictService {

    private final DistrictRepository districtRepository;

    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    // Получение всех неархивных районов с фильтрацией
    // public List<District> getDistricts(String name, String code) {
    // if (name != null) {
    // return districtRepository.findByNameContainingIgnoreCase(name);
    // } else if (code != null) {
    // return districtRepository.findByCode(code);
    // }
    // return districtRepository.findByArchivedFalse();
    // }

    // Получение всех неархивных районов с фильтрацией
    public List<DistrictDtoWithoutArchive> getDistricts(Long id, String name, String code) {

        List<District> districts;

        if (name != null) {
            districts = districtRepository.findByNameContainingIgnoreCaseAndArchivedFalse(name);
        } else if (code != null) {
            districts = districtRepository.findByCodeAndArchivedFalse(code);
        }
        districts = districtRepository.findByArchivedFalse();

        return districts.stream()
                .map(d -> new DistrictDtoWithoutArchive(d))
                .collect(Collectors.toList());
    }

    // public List<DistrictDto> getDistricts(String name, String code) {
    // if (name != null) {
    // return
    // districtRepository.findByNameContainingIgnoreCaseAndArchivedFalse(name).stream()
    // .map(d -> new DistrictDto(d.getName(),
    // d.getCode())).collect(Collectors.toList());
    // } else if (code != null) {
    // return districtRepository.findByCodeAndArchivedFalse(code).stream()
    // .map(d -> new DistrictDto(d.getName(),
    // d.getCode())).collect(Collectors.toList());
    // }
    // return districtRepository.findByArchivedFalse().stream()
    // .map(d -> new DistrictDto(d.getName(),
    // d.getCode())).collect(Collectors.toList());
    // }

    // Добавление нового района
    public District addDistrict(District district) {
        if (districtRepository.findByNameContainingIgnoreCase(district.getName()).isEmpty()) {
            return districtRepository.save(district);
        } else {
            throw new IllegalArgumentException("Район с таким названием уже существует");
        }
    }

    // Архивирование района
    public District archiveDistrict(Long id) {
        District district = districtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Район не найден"));
        district.setArchived(true);
        return districtRepository.save(district);
    }

    // Удаление района
    public void deleteDistrict(Long id) {
        if (!districtRepository.existsById(id)) {
            throw new RuntimeException("District with id " + id + " not found"); // Можно заменить на кастомное
                                                                                 // исключение
        }
        districtRepository.deleteById(id);
    }
}
