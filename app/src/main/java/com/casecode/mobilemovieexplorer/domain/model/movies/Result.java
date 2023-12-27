package com.casecode.mobilemovieexplorer.domain.model.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Record representing a movie result.
 *
 * @param adult            Indicates whether the movie is for adults.
 * @param backdropPath     The backdrop path for the movie.
 * @param genreIds         The genre IDs associated with the movie.
 * @param id               The ID of the movie.
 * @param originalLanguage The original language of the movie.
 * @param originalTitle    The original title of the movie.
 * @param overview         An overview or summary of the movie.
 * @param popularity       The popularity score of the movie.
 * @param posterPath       The poster path for the movie.
 * @param releaseDate      The release date of the movie.
 * @param title            The title of the movie.
 * @param video            Indicates whether the movie has video content.
 * @param voteAverage      The average vote score for the movie.
 * @param voteCount        The count of votes for the movie.
 */
public record Result(
        boolean adult,
        @SerializedName("backdrop_path") String backdropPath,
        @SerializedName("genre_ids") List<Integer> genreIds,
        int id,
        @SerializedName("original_language") String originalLanguage,
        @SerializedName("original_title") String originalTitle,
        String overview,
        double popularity,
        @SerializedName("poster_path") String posterPath,
        @SerializedName("release_date") String releaseDate,
        String title,
        boolean video,
        @SerializedName("vote_average") double voteAverage,
        @SerializedName("vote_count") int voteCount
) {
}