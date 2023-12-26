package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class BelongsToCollection {
    @SerializedName("backdrop_path")
    private String backdropPath;

    private int id;
    private String name;

    @SerializedName("poster_path")
    private String posterPath;

    public BelongsToCollection(String backdropPath, int id, String name, String posterPath) {
        this.backdropPath = backdropPath;
        this.id = id;
        this.name = name;
        this.posterPath = posterPath;
    }


}