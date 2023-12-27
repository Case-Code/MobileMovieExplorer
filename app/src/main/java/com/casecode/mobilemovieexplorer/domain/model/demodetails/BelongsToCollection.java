package com.casecode.mobilemovieexplorer.domain.model.demodetails;

import com.google.gson.annotations.SerializedName;

/**
 * Record representing a collection to which a movie belongs.
 *
 * @param backdropPath The backdrop path for the collection.
 * @param id           The ID of the collection.
 * @param name         The name of the collection.
 * @param posterPath   The poster path for the collection.
 */
public record BelongsToCollection(
        @SerializedName("backdrop_path") String backdropPath,
        int id,
        String name,
        @SerializedName("poster_path") String posterPath
) {
}