package com.example.dto;

import com.example.respak.model.District;

public class DistrictDtoWithoutArchive {
    private Long id;
    private String name;
    private String code;

    public DistrictDtoWithoutArchive(District district) {
        this.id = district.getId();
        this.name = district.getName();
        this.code = district.getCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
