package com.casecode.mobilemovieexplorer.domain.model.demodetails;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class SpokenLanguage {
    @SerializedName("english_name")
    private final String englishName;

    @SerializedName("iso_639_1")
    private final String iso6391;

    private final String name;

    public SpokenLanguage(String englishName, String iso6391, String name) {
        this.englishName = englishName;
        this.iso6391 = iso6391;
        this.name = name;
    }


}