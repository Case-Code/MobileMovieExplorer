package com.casecode.mobilemovieexplorer.domain.model.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

public record MoviesResponse(int page, List<Result> results,
                             @SerializedName("total_pages") int totalPages,
                             @SerializedName("total_results") int totalResults) {


}
