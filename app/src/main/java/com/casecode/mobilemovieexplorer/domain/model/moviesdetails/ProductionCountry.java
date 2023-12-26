package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import lombok.Data;

@Data
public class ProductionCountry {
    private String iso31661;
    private String name;

    public ProductionCountry(String iso31661, String name) {
        this.iso31661 = iso31661;
        this.name = name;
    }



    // Other methods as needed...
}