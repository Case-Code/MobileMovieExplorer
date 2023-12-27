package com.casecode.mobilemovieexplorer.domain.model.demo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * Data class representing the response from a demo API.
 */
@Data
public class DemoResponse {

    /**
     * List of results in the demo response.
     */
    @SerializedName("results")
    private List<DemoMovie> results;

    /**
     * Constructs a new {@code DemoResponse} with the given list of results.
     *
     * @param results The list of results in the demo response.
     */
    public DemoResponse(List<DemoMovie> results) {
        this.results = results;
    }
}