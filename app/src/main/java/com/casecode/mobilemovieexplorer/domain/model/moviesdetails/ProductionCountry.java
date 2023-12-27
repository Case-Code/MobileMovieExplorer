package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import lombok.Data;

/**
 * Class representing a production country associated with a movie.
 */
@Data
public class ProductionCountry {
    private String iso31661;
    private String name;

    /**
     * Constructs a new instance of the ProductionCountry class with information about the country.
     *
     * @param iso31661 The ISO 3166-1 code of the country.
     * @param name     The name of the country.
     */
    public ProductionCountry(String iso31661, String name) {
        this.iso31661 = iso31661;
        this.name = name;
    }

    // Other methods as needed...
}