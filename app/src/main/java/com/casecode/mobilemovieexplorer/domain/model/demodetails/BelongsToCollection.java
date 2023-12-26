package com.casecode.mobilemovieexplorer.domain.model.demodetails;

import com.google.gson.annotations.SerializedName;

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
}