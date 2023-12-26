package com.casecode.mobilemovieexplorer.domain.model.demodetails;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

public record ProductionCompany(int id, @SerializedName("logo_path") String logoPath, String name,
                                @SerializedName("origin_country") String originCountry) {


}