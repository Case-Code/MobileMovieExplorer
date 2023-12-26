package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import com.google.gson.annotations.SerializedName;

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

    public String getBackdropPath() {
        return backdropPath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    // Other methods as needed...
}