package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import lombok.Data;

@Data
public class ProductionCompany {
    private int id;
    private String logoPath;
    private String name;
    private String originCountry;

    public ProductionCompany(int id, String logoPath, String name, String originCountry) {
        this.id = id;
        this.logoPath = logoPath;
        this.name = name;
        this.originCountry = originCountry;
    }



    // Other methods as needed...
}