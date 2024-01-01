package com.casecode.mobilemovieexplorer.domain.model.demo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Data class representing the response from a demo API.
 */
public record DemoResponse(

        @SerializedName("results")
        List<DemoMovie> results)
{}