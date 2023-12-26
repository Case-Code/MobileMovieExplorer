package com.casecode.mobilemovieexplorer.domain.model.demo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DemoResponse {
    @SerializedName("results")
    private List<Result> results;

    public DemoResponse(List<Result> results) {
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }

    // Other methods as needed...
}