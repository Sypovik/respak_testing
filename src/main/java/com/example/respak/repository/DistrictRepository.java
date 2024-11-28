package com.example.respak.repository;

import com.example.respak.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long> {

    // Получение списка районов с фильтрацией по названию
    List<District> findByNameContainingIgnoreCase(String name);

    // Получение списка районов с фильтрацией по коду
    List<District> findByCode(String code);

    // Получение всех неархивных районов
    List<District> findByArchivedFalse();

    boolean existsById(String name);

    // void deleteById(Long id);
}
