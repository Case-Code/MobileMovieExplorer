package com.casecode.mobilemovieexplorer.domain.model.demo;

import java.util.List;

public class DemoResponse {
    private final List<Result> results;

    public DemoResponse(List<Result> results) {
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }
}