package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

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

    public int getId() {
        return id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getName() {
        return name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    // Other methods as needed...
}