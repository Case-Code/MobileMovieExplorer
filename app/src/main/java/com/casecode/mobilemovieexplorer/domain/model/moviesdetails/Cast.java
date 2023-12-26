package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import com.google.gson.annotations.SerializedName;

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

    public boolean isAdult() {
        return adult;
    }

    public int getCastId() {
        return castId;
    }

    public String getCharacter() {
        return character;
    }

    public String getCreditId() {
        return creditId;
    }

    public int getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
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

    // Other methods as needed...
}