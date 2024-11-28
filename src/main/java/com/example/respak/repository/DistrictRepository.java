package com.example.respak.repository;

import com.example.respak.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    // Получение списка районов с фильтрацией по названию
    List<District> findByNameContainingIgnoreCaseAndArchivedFalse(String name);

    List<District> findByNameContainingIgnoreCase(String name);

    // Получение списка районов с фильтрацией по коду
    List<District> findByCodeAndArchivedFalse(String code);

    // Получение всех неархивных районов
    List<District> findByArchivedFalse();

    boolean existsById(String name);

    // void deleteById(Long id);
}
