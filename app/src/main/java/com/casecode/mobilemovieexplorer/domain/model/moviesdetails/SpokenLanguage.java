package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

public class SpokenLanguage {
    private String englishName;
    private String iso6391;
    private String name;

    public SpokenLanguage(String englishName, String iso6391, String name) {
        this.englishName = englishName;
        this.iso6391 = iso6391;
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getIso6391() {
        return iso6391;
    }

    public String getName() {
        return name;
    }

    // Other methods as needed...
}