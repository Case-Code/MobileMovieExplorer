package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Class representing a crew member associated with a movie or TV show.
 */
@Data
public class Crew {
    private boolean adult;

    @SerializedName("credit_id")
    private String creditId;

    private String department;
    private int gender;
    private int id;
    private String job;

    @SerializedName("known_for_department")
    private String knownForDepartment;

    private String name;

    @SerializedName("original_name")
    private String originalName;

    private double popularity;

    @SerializedName("profile_path")
    private String profilePath;

    /**
     * Constructs a new instance of the Crew class.
     *
     * @param adult              Indicates whether the crew member is an adult.
     * @param creditId           The credit ID associated with the crew member.
     * @param department         The department in the entertainment industry for which the crew member is associated.
     * @param gender             The gender of the crew member.
     * @param id                 The ID of the crew member.
     * @param job                The job or role of the crew member.
     * @param knownForDepartment The department in the entertainment industry for which the crew member is known.
     * @param name               The name of the crew member.
     * @param originalName       The original name of the crew member.
     * @param popularity         The popularity score of the crew member.
     * @param profilePath        The profile path for the crew member.
     */
    public Crew(
            boolean adult,
            String creditId,
            String department,
            int gender,
            int id,
            String job,
            String knownForDepartment,
            String name,
            String originalName,
            double popularity,
            String profilePath
    ) {
        this.adult = adult;
        this.creditId = creditId;
        this.department = department;
        this.gender = gender;
        this.id = id;
        this.job = job;
        this.knownForDepartment = knownForDepartment;
        this.name = name;
        this.originalName = originalName;
        this.popularity = popularity;
        this.profilePath = profilePath;
    }

    // Other methods as needed...
}