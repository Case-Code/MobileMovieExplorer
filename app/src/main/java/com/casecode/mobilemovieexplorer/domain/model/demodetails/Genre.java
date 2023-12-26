package com.casecode.mobilemovieexplorer.domain.model.demodetails;

import lombok.Data;

@Data
public class Genre {
    private final int id;
    private final String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }


}