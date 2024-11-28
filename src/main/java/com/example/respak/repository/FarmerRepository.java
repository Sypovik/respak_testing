package com.example.respak.repository;

import com.example.respak.model.Farmer;
import com.example.respak.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

    // Получение списка фермеров с фильтрацией по названию организации
    List<Farmer> findByOrganizationNameContainingIgnoreCase(String name);

    // Фильтрация по организационно-правовой форме
    List<Farmer> findByLegalForm(Farmer.LegalForm legalForm);

    // Фильтрация по ИНН
    List<Farmer> findByInn(String inn);

    // Фильтрация по району регистрации
    List<Farmer> findByRegistrationDistrict(District district);

    // Фильтрация по дате регистрации
    List<Farmer> findByRegistrationDateBetween(LocalDate startDate, LocalDate endDate);

    // Получение всех неархивных фермеров
    List<Farmer> findByArchivedFalse();
}
