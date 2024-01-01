package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import com.google.gson.annotations.SerializedName;

/**
 * Class representing a crew member associated with a movie or TV show.
 */

public record Crew(
        boolean adult,

        @SerializedName("credit_id")
        String creditId,

        String department,
        int gender,
        int id,
        String job,

        @SerializedName("known_for_department")
        String knownForDepartment,

        String name,

        @SerializedName("original_name")
        String originalName,

        double popularity,

        @SerializedName("profile_path")
        String profilePath) {


}