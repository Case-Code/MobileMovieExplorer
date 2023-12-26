package com.casecode.mobilemovieexplorer.domain.model.demodetails;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

public record DemoDetailsResponse(boolean adult,
                                  @SerializedName("backdrop_path") String backdropPath,
                                  @SerializedName("belongs_to_collection") BelongsToCollection belongsToCollection,
                                  int budget, List<Genre> genres, String homepage, int id,
                                  @SerializedName("imdb_id") String imdbId,
                                  @SerializedName("original_language") String originalLanguage,
                                  @SerializedName("original_title") String originalTitle,
                                  String overview, double popularity,
                                  @SerializedName("poster_path") String posterPath,
                                  @SerializedName("production_companies") List<ProductionCompany> productionCompanies,
                                  @SerializedName("production_countries") List<ProductionCountry> productionCountries,
                                  @SerializedName("release_date") String releaseDate, int revenue,
                                  int runtime,
                                  @SerializedName("spoken_languages") List<SpokenLanguage> spokenLanguages,
                                  String status, String tagline, String title, boolean video,
                                  @SerializedName("vote_average") double voteAverage,
                                  @SerializedName("vote_count") int voteCount) {
}