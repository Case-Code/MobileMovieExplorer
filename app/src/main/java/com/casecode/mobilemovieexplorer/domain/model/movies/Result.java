package com.casecode.mobilemovieexplorer.domain.model.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

public record Result(boolean adult, @SerializedName("backdrop_path") String backdropPath,
                     @SerializedName("genre_ids") List<Integer> genreIds, int id,
                     @SerializedName("original_language") String originalLanguage,
                     @SerializedName("original_title") String originalTitle, String overview,
                     double popularity, @SerializedName("poster_path") String posterPath,
                     @SerializedName("release_date") String releaseDate, String title,
                     boolean video, @SerializedName("vote_average") double voteAverage,
                     @SerializedName("vote_count") int voteCount) {


}