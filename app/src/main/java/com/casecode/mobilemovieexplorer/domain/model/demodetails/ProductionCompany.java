package com.casecode.mobilemovieexplorer.domain.model.demodetails;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ProductionCompany {
    private final int id;

    @SerializedName("logo_path")
    private final String logoPath;

    private final String name;

    @SerializedName("origin_country")
    private final String originCountry;

    public ProductionCompany(int id, String logoPath, String name, String originCountry) {
        this.id = id;
        this.logoPath = logoPath;
        this.name = name;
        this.originCountry = originCountry;
    }


}