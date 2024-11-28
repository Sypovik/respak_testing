package com.example.respak.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "farmer")
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String organizationName; // Название организации

    public enum LegalForm {
        ЮР, // Юридическое лицо
        ИП, // Индивидуальный предприниматель
        ФЛ // Физическое лицо
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LegalForm legalForm; // Organizational and legal form

    @Column(nullable = false, unique = true, length = 12)
    private String inn; // ИНН

    @Column(length = 9)
    private String kpp; // КПП

    @Column(length = 13)
    private String ogrn; // ОГРН

    @ManyToOne
    @JoinColumn(name = "registration_district_id", nullable = false)
    private District registrationDistrict; // Район регистрации

    @ManyToMany
    @JoinTable(name = "farmer_districts", joinColumns = @JoinColumn(name = "farmer_id"), inverseJoinColumns = @JoinColumn(name = "district_id"))
    private List<District> sowingDistricts; // Районы посевных полей

    @Column(nullable = false)
    private LocalDate registrationDate; // Дата регистрации

    @Column(nullable = false)
    private boolean archived; // Статус архивности

    // Getters and Setters
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

    public boolean getArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
