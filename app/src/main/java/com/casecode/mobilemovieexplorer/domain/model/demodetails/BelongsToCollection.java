package com.casecode.mobilemovieexplorer.domain.model.demodetails;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class BelongsToCollection {
    @SerializedName("backdrop_path")
    private final String backdropPath;

    private final int id;
    private final String name;

    @SerializedName("poster_path")
    private final String posterPath;

    public BelongsToCollection(String backdropPath, int id, String name, String posterPath) {
        this.backdropPath = backdropPath;
        this.id = id;
        this.name = name;
        this.posterPath = posterPath;
    }


}