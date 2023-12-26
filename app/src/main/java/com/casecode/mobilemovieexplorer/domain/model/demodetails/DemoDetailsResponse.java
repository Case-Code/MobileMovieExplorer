package com.casecode.mobilemovieexplorer.domain.model.demodetails;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DemoDetailsResponse {
    private final boolean adult;

    @SerializedName("backdrop_path")
    private final String backdropPath;

    @SerializedName("belongs_to_collection")
    private final BelongsToCollection belongsToCollection;

    private final int budget;
    private final List<Genre> genres;
    private final String homepage;
    private final int id;

    @SerializedName("imdb_id")
    private final String imdbId;

    @SerializedName("original_language")
    private final String originalLanguage;

    @SerializedName("original_title")
    private final String originalTitle;

    private final String overview;
    private final double popularity;

    @SerializedName("poster_path")
    private final String posterPath;

    @SerializedName("production_companies")
    private final List<ProductionCompany> productionCompanies;

    @SerializedName("production_countries")
    private final List<ProductionCountry> productionCountries;

    @SerializedName("release_date")
    private final String releaseDate;

    private final int revenue;
    private final int runtime;

    @SerializedName("spoken_languages")
    private final List<SpokenLanguage> spokenLanguages;

    private final String status;
    private final String tagline;
    private final String title;
    private final boolean video;

    @SerializedName("vote_average")
    private final double voteAverage;

    @SerializedName("vote_count")
    private final int voteCount;

    public DemoDetailsResponse(boolean adult, String backdropPath, BelongsToCollection belongsToCollection,
                               int budget, List<Genre> genres, String homepage, int id, String imdbId,
                               String originalLanguage, String originalTitle, String overview, double popularity,
                               String posterPath, List<ProductionCompany> productionCompanies,
                               List<ProductionCountry> productionCountries, String releaseDate, int revenue,
                               int runtime, List<SpokenLanguage> spokenLanguages, String status, String tagline,
                               String title, boolean video, double voteAverage, int voteCount) {
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.belongsToCollection = belongsToCollection;
        this.budget = budget;
        this.genres = genres;
        this.homepage = homepage;
        this.id = id;
        this.imdbId = imdbId;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.productionCompanies = productionCompanies;
        this.productionCountries = productionCountries;
        this.releaseDate = releaseDate;
        this.revenue = revenue;
        this.runtime = runtime;
        this.spokenLanguages = spokenLanguages;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.video = video;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }
}