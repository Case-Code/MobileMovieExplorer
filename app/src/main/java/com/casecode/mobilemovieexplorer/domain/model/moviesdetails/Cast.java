package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Class representing a cast member associated with a movie or TV show.
 */
public record Cast (
     boolean adult,

    @SerializedName("cast_id")
     int castId,

     String character,

    @SerializedName("credit_id")
     String creditId,

     int gender,
     int id,

    @SerializedName("known_for_department")
     String knownForDepartment,

     String name,
     int order,

    @SerializedName("original_name")
     String originalName,

     double popularity,

    @SerializedName("profile_path")
     String profilePath){


}