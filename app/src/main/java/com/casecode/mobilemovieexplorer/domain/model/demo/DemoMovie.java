package com.casecode.mobilemovieexplorer.domain.model.demo;

import com.google.gson.annotations.SerializedName;


/**
 * Record representing a result from a movie API.
 *
 * @param backdropPath  The backdrop path for the movie.
 * @param id            The ID of the movie.
 * @param originalTitle The original title of the movie.
 * @param overview      An overview or summary of the movie.
 * @param popularity    The popularity score of the movie.
 * @param posterPath    The poster path for the movie.
 * @param releaseDate   The release date of the movie.
 * @param title         The title of the movie.
 * @param voteAverage   The average vote score for the movie.
 * @param voteCount     The count of votes for the movie.
 */
public record DemoMovie(
        @SerializedName("backdrop_path") String backdropPath,
        int id,
        @SerializedName("original_title") String originalTitle,
        String overview,
        double popularity,
        @SerializedName("poster_path") String posterPath,
        @SerializedName("release_date") String releaseDate,
        String title,
        @SerializedName("vote_average") double voteAverage,
        @SerializedName("vote_count") int voteCount
) {
}