package com.casecode.mobilemovieexplorer.domain.model.demo;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("backdrop_path")
    private final String backdropPath;

    private final int id;

    @SerializedName("original_title")
    private final String originalTitle;

    private final String overview;
    private final double popularity;

    @SerializedName("poster_path")
    private final String posterPath;

    @SerializedName("release_date")
    private final String releaseDate;

    private final String title;

    @SerializedName("vote_average")
    private final double voteAverage;

    @SerializedName("vote_count")
    private final int voteCount;

    public Result(String backdropPath, int id, String originalTitle, String overview, double popularity,
                  String posterPath, String releaseDate, String title, double voteAverage, int voteCount) {
        this.backdropPath = backdropPath;
        this.id = id;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.title = title;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }
}