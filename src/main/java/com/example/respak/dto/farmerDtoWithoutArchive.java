
package com.example.respak.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.example.respak.model.Farmer;
import com.example.respak.model.Farmer.LegalForm;

public class farmerDtoWithoutArchive {
    private Long id;
    private String organizationName;
    private Farmer.LegalForm legalForm;
    private String inn;
    private String kpp;
    private String ogrn;
    private DistrictDtoWithoutArchive registrationDistrict;
    private List<DistrictDtoWithoutArchive> sowingDistricts;
    private LocalDate registrationDate;

    public farmerDtoWithoutArchive(Farmer farmer) {
        DistrictDtoWithoutArchive _registrationDistrict = new DistrictDtoWithoutArchive(
                farmer.getRegistrationDistrict());
        List<DistrictDtoWithoutArchive> _sowingDistricts = farmer.getSowingDistricts().stream()
                .map(d -> new DistrictDtoWithoutArchive(d))
                .collect(Collectors.toList());
        this.id = farmer.getId();
        this.organizationName = farmer.getOrganizationName();
        this.legalForm = farmer.getLegalForm();
        this.inn = farmer.getInn();
        this.kpp = farmer.getKpp();
        this.ogrn = farmer.getOgrn();
        this.registrationDistrict = _registrationDistrict;
        this.sowingDistricts = _sowingDistricts;
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

    public DistrictDtoWithoutArchive getRegistrationDistrict() {
        return registrationDistrict;
    }

    public void setRegistrationDistrict(DistrictDtoWithoutArchive registrationDistrict) {
        this.registrationDistrict = registrationDistrict;
    }

    public List<DistrictDtoWithoutArchive> getSowingDistricts() {
        return sowingDistricts;
    }

    public void setSowingDistricts(List<DistrictDtoWithoutArchive> sowingDistricts) {
        this.sowingDistricts = sowingDistricts;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

}