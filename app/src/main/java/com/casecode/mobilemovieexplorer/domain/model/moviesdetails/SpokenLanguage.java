package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

/**
 * Class representing a spoken language associated with a movie.
 */

public record SpokenLanguage(
        String englishName,
        String iso6391,
        String name) {


}