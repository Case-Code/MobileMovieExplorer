package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import lombok.Data;

@Data
public class SpokenLanguage {
    private String englishName;
    private String iso6391;
    private String name;

    public SpokenLanguage(String englishName, String iso6391, String name) {
        this.englishName = englishName;
        this.iso6391 = iso6391;
        this.name = name;
    }



    // Other methods as needed...
}