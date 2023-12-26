package com.casecode.mobilemovieexplorer.domain.model.demodetails;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

public record BelongsToCollection(@SerializedName("backdrop_path") String backdropPath, int id,
                                  String name, @SerializedName("poster_path") String posterPath) {


}