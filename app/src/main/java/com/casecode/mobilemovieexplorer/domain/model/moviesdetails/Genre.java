package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import lombok.Data;

@Data
public class Genre {
    private int id;
    private String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }



    // Other methods as needed...
}