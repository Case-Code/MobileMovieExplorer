package com.casecode.mobilemovieexplorer.domain.model.demodetails;

import com.google.gson.annotations.SerializedName;

/**
 * Record representing a production country associated with a movie.
 *
 * @param iso31661 The ISO 3166-1 code of the production country.
 * @param name     The name of the production country.
 */
public record ProductionCountry(
        @SerializedName("iso_3166_1") String iso31661,
        String name
) {
}