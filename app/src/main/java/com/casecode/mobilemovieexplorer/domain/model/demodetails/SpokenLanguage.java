package com.casecode.mobilemovieexplorer.domain.model.demodetails;

import com.google.gson.annotations.SerializedName;

/**
 * Record representing a spoken language associated with a movie.
 *
 * @param englishName The English name of the spoken language.
 * @param iso6391     The ISO 639-1 code of the spoken language.
 * @param name        The name of the spoken language.
 */
public record SpokenLanguage(
        @SerializedName("english_name") String englishName,
        @SerializedName("iso_639_1") String iso6391,
        String name
) {
}