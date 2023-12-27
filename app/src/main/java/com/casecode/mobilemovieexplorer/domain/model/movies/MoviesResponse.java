package com.casecode.mobilemovieexplorer.domain.model.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Record representing a response containing a list of movies.
 *
 * @param page         The page number of the response.
 * @param results      The list of movie results.
 * @param totalPages   The total number of pages.
 * @param totalResults The total number of results.
 */
public record MoviesResponse(
        int page,
        List<Movie> results,
        @SerializedName("total_pages") int totalPages,
        @SerializedName("total_results") int totalResults
) {
}
