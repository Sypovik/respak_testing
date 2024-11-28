
package com.example.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.respak.model.District;
import com.example.respak.model.Farmer;
import com.example.respak.model.Farmer.LegalForm;

public class farmerDtoWithoutArchive {
    private Long id;
    private String organizationName;
    private Farmer.LegalForm legalForm;
    private String inn;
    private String kpp;
    private String ogrn;
    private District registrationDistrict;
    private List<District> sowingDistricts;
    private LocalDate registrationDate;

    public farmerDtoWithoutArchive(Farmer farmer) {
        this.id = farmer.getId();
        this.organizationName = farmer.getOrganizationName();
        this.legalForm = farmer.getLegalForm();
        this.inn = farmer.getInn();
        this.kpp = farmer.getKpp();
        this.ogrn = farmer.getOgrn();
        this.registrationDistrict = farmer.getRegistrationDistrict();
        this.sowingDistricts = farmer.getSowingDistricts();
        this.registrationDate = farmer.getRegistrationDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public LegalForm getLegalForm() {
        return legalForm;
    }

    public void setLegalForm(LegalForm legalForm) {
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

    public District getRegistrationDistrict() {
        return registrationDistrict;
    }

    public void setRegistrationDistrict(District registrationDistrict) {
        this.registrationDistrict = registrationDistrict;
    }

    public List<District> getSowingDistricts() {
        return sowingDistricts;
    }

    public void setSowingDistricts(List<District> sowingDistricts) {
        this.sowingDistricts = sowingDistricts;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

}