package com.example.respak.repository;

import com.example.respak.model.Farmer;
import com.example.respak.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

    // Получение списка фермеров с фильтрацией по названию организации
    List<Farmer> findByOrganizationNameContainingIgnoreCaseAndArchivedFalse(String name);

    // Фильтрация по организационно-правовой форме
    List<Farmer> findByLegalFormAndArchivedFalse(Farmer.LegalForm legalForm);

    // Фильтрация по ИНН
    List<Farmer> findByInnAndArchivedFalse(String inn);

    List<Farmer> findByInn(String inn);

    // Фильтрация по району регистрации
    List<Farmer> findByRegistrationDistrictAndArchivedFalse(District district);

    // Фильтрация по дате регистрации
    List<Farmer> findByRegistrationDateBetweenAndArchivedFalse(LocalDate startDate, LocalDate endDate);

    // Получение всех архивных фермеров
    List<Farmer> findByArchivedFalse();
}
