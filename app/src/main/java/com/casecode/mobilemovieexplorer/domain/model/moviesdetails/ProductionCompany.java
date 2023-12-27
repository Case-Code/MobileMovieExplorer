package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import lombok.Data;

/**
 * Class representing a production company associated with a movie.
 */
@Data
public class ProductionCompany {
    private int id;
    private String logoPath;
    private String name;
    private String originCountry;

    /**
     * Constructs a new instance of the ProductionCompany class with information about the company.
     *
     * @param id            The unique identifier of the production company.
     * @param logoPath      The path to the logo image of the production company.
     * @param name          The name of the production company.
     * @param originCountry The origin country of the production company.
     */
    public ProductionCompany(int id, String logoPath, String name, String originCountry) {
        this.id = id;
        this.logoPath = logoPath;
        this.name = name;
        this.originCountry = originCountry;
    }

    // Other methods as needed...
}