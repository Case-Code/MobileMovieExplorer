package com.casecode.mobilemovieexplorer.domain.model.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Result {
    private final boolean adult;

    @SerializedName("backdrop_path")
    private final String backdropPath;

    @SerializedName("genre_ids")
    private final List<Integer> genreIds;

    private final int id;

    @SerializedName("original_language")
    private final String originalLanguage;

    @SerializedName("original_title")
    private final String originalTitle;

    private final String overview;
    private final double popularity;

    @SerializedName("poster_path")
    private final String posterPath;

    @SerializedName("release_date")
    private final String releaseDate;

    private final String title;
    private final boolean video;

    @SerializedName("vote_average")
    private final double voteAverage;

    @SerializedName("vote_count")
    private final int voteCount;

    public Result(boolean adult, String backdropPath, List<Integer> genreIds, int id, String originalLanguage,
                  String originalTitle, String overview, double popularity, String posterPath,
                  String releaseDate, String title, boolean video, double voteAverage, int voteCount) {
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.genreIds = genreIds;
        this.id = id;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.title = title;
        this.video = video;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }


}