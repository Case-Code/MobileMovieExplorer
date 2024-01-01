package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import com.casecode.mobilemovieexplorer.domain.model.demodetails.Genre;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Class representing details of a movie, including information about its cast, crew, genres, and more.
 */
public record MoviesDetailsResponse(
        @SerializedName("adult")
        boolean adult,

        @SerializedName("backdrop_path")
        String backdropPath,

        @SerializedName("belongs_to_collection")
        BelongsToCollection belongsToCollection,

        @SerializedName("budget")
        int budget,

        @SerializedName("cast")
        List<Cast> cast,

        @SerializedName("crew")
        List<Crew> crew,

        @SerializedName("genres")
        List<Genre> genres,

        @SerializedName("homepage")
        String homepage,

        @SerializedName("id")
        int id,

        @SerializedName("imdb_id")
        String imdbId,

        @SerializedName("original_language")
        String originalLanguage,

        @SerializedName("original_title")
        String originalTitle,

        @SerializedName("overview")
        String overview,

        @SerializedName("popularity")
        double popularity,

        @SerializedName("poster_path")
        String posterPath,

        @SerializedName("production_companies")
        List<ProductionCompany> productionCompanies,

        @SerializedName("production_countries")
        List<ProductionCountry> productionCountries,

        @SerializedName("release_date")
        String releaseDate,

        @SerializedName("revenue")
        int revenue,

        @SerializedName("runtime")
        int runtime,

        @SerializedName("spoken_languages")
        List<SpokenLanguage> spokenLanguages,

        @SerializedName("status")
        String status,

        @SerializedName("tagline")
        String tagline,

        @SerializedName("title")
        String title,

        @SerializedName("video")
        boolean video,

        @SerializedName("vote_average")
        double voteAverage,

        @SerializedName("vote_count")
        int voteCount
) {

}