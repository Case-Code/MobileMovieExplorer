package com.casecode.mobilemovieexplorer.domain.model.demodetails;

import com.google.gson.annotations.SerializedName;

/**
 * Record representing a production company associated with a movie.
 *
 * @param id            The ID of the production company.
 * @param logoPath      The logo path for the production company.
 * @param name          The name of the production company.
 * @param originCountry The origin country of the production company.
 */
public record ProductionCompany(
        int id,
        @SerializedName("logo_path") String logoPath,
        String name,
        @SerializedName("origin_country") String originCountry
) {
}