package com.casecode.mobilemovieexplorer.domain.model.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class MoviesResponse {
    private final int page;
    private final List<Result> results;

    @SerializedName("total_pages")
    private final int totalPages;

    @SerializedName("total_results")
    private final int totalResults;

    public MoviesResponse(int page, List<Result> results, int totalPages, int totalResults) {
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }


}
