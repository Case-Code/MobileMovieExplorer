package com.casecode.mobilemovieexplorer.domain.model.demodetails;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ProductionCountry {
    @SerializedName("iso_3166_1")
    private final String iso31661;

    private final String name;

    public ProductionCountry(String iso31661, String name) {
        this.iso31661 = iso31661;
        this.name = name;
    }


}