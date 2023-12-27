package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Class representing a cast member associated with a movie or TV show.
 */
@Data
public class Cast {
    private boolean adult;

    @SerializedName("cast_id")
    private int castId;

    private String character;

    @SerializedName("credit_id")
    private String creditId;

    private int gender;
    private int id;

    @SerializedName("known_for_department")
    private String knownForDepartment;

    private String name;
    private int order;

    @SerializedName("original_name")
    private String originalName;

    private double popularity;

    @SerializedName("profile_path")
    private String profilePath;

    /**
     * Constructs a new instance of the Cast class.
     *
     * @param name        The name of the cast member.
     * @param profilePath The profile path for the cast member.
     */
    public Cast(String name, String profilePath) {
        this.name = name;
        this.profilePath = profilePath;
    }

    /**
     * Constructs a new instance of the Cast class.
     *
     * @param adult              Indicates whether the cast member is an adult.
     * @param castId             The cast ID associated with the member.
     * @param character          The character played by the cast member.
     * @param creditId           The credit ID associated with the cast member.
     * @param gender             The gender of the cast member.
     * @param id                 The ID of the cast member.
     * @param knownForDepartment The department in the entertainment industry for which the cast member is known.
     * @param name               The name of the cast member.
     * @param order              The order in which the cast member appears.
     * @param originalName       The original name of the cast member.
     * @param popularity         The popularity score of the cast member.
     * @param profilePath        The profile path for the cast member.
     */
    public Cast(
            boolean adult,
            int castId,
            String character,
            String creditId,
            int gender,
            int id,
            String knownForDepartment,
            String name,
            int order,
            String originalName,
            double popularity,
            String profilePath
    ) {
        this.adult = adult;
        this.castId = castId;
        this.character = character;
        this.creditId = creditId;
        this.gender = gender;
        this.id = id;
        this.knownForDepartment = knownForDepartment;
        this.name = name;
        this.order = order;
        this.originalName = originalName;
        this.popularity = popularity;
        this.profilePath = profilePath;
    }
}