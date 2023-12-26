package com.casecode.mobilemovieexplorer.domain.model.demodetails;

import com.google.gson.annotations.SerializedName;


public record SpokenLanguage(@SerializedName("english_name") String englishName,
                             @SerializedName("iso_639_1") String iso6391, String name) {


}