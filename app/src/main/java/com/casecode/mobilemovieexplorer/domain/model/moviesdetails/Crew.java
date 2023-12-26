package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

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