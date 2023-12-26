package com.casecode.mobilemovieexplorer.domain.model.demodetails;

import com.google.gson.annotations.SerializedName;

import lombok.Data;


public record ProductionCountry(@SerializedName("iso_3166_1") String iso31661, String name) {


}