package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import lombok.Data;

/**
 * Class representing a spoken language associated with a movie.
 */
@Data
public class SpokenLanguage {
    private String englishName;
    private String iso6391;
    private String name;

    /**
     * Constructs a new instance of the SpokenLanguage class with information about the language.
     *
     * @param englishName The English name of the language.
     * @param iso6391     The ISO 639-1 code of the language.
     * @param name        The name of the language.
     */
    public SpokenLanguage(String englishName, String iso6391, String name) {
        this.englishName = englishName;
        this.iso6391 = iso6391;
        this.name = name;
    }

    // Other methods as needed...
}