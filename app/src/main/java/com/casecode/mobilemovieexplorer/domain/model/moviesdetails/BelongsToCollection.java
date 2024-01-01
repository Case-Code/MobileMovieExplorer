package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Class representing a collection to which a movie belongs.
 */
public record BelongsToCollection(
        @SerializedName("backdrop_path")
        String backdropPath,

        int id,
        String name,

        @SerializedName("poster_path")
        String posterPath) {

}