package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import com.google.gson.annotations.SerializedName;

public class Crew {
    private final boolean adult;

    @SerializedName("credit_id")
    private final String creditId;

    private final String department;
    private final int gender;
    private final int id;
    private final String job;

    @SerializedName("known_for_department")
    private final String knownForDepartment;

    private final String name;

    @SerializedName("original_name")
    private final String originalName;

    private final double popularity;

    @SerializedName("profile_path")
    private final String profilePath;

    public Crew(boolean adult, String creditId, String department, int gender, int id, String job,
                String knownForDepartment, String name, String originalName, double popularity, String profilePath) {
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

    public boolean isAdult() {
        return adult;
    }

    public String getCreditId() {
        return creditId;
    }

    public String getDepartment() {
        return department;
    }

    public int getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getProfilePath() {
        return profilePath;
    }
}