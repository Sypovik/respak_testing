package com.example.respak.dto;

import java.time.LocalDate;
import java.util.List;

public class FarmerDto {
    private String organizationName;
    private String legalForm;
    private String inn;
    private String kpp;
    private String ogrn;
    private Long registrationDistrictId; // ID вместо объекта
    private List<Long> sowingDistrictIds; // Список ID
    private LocalDate registrationDate;
    private boolean archived;

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getLegalForm() {
        return legalForm;
    }

    public void setLegalForm(String legalForm) {
        this.legalForm = legalForm;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public Long getRegistrationDistrictId() {
        return registrationDistrictId;
    }

    public void setRegistrationDistrictId(Long registrationDistrictId) {
        this.registrationDistrictId = registrationDistrictId;
    }

    public List<Long> getSowingDistrictIds() {
        return sowingDistrictIds;
    }

    public void setSowingDistrictIds(List<Long> sowingDistrictIds) {
        this.sowingDistrictIds = sowingDistrictIds;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
    // Getters and Setters
}
