package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Class representing a collection to which a movie belongs.
 */
@Data
public class BelongsToCollection {
    @SerializedName("backdrop_path")
    private String backdropPath;

    private int id;
    private String name;

    @SerializedName("poster_path")
    private String posterPath;

    /**
     * Constructs a new instance of the BelongsToCollection class.
     *
     * @param backdropPath The backdrop path for the collection.
     * @param id           The ID of the collection.
     * @param name         The name of the collection.
     * @param posterPath   The poster path for the collection.
     */
    public BelongsToCollection(String backdropPath, int id, String name, String posterPath) {
        this.backdropPath = backdropPath;
        this.id = id;
        this.name = name;
        this.posterPath = posterPath;
    }
}