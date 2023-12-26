package com.casecode.mobilemovieexplorer.domain.model.demo;

import com.google.gson.annotations.SerializedName;


public record Result(@SerializedName("backdrop_path") String backdropPath, int id,
                     @SerializedName("original_title") String originalTitle, String overview,
                     double popularity, @SerializedName("poster_path") String posterPath,
                     @SerializedName("release_date") String releaseDate, String title,
                     @SerializedName("vote_average") double voteAverage,
                     @SerializedName("vote_count") int voteCount) {

}