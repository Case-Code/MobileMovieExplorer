package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import lombok.Data;

/**
 * Class representing a genre associated with a movie or TV show.
 */
@Data
public class Genre {
    private int id;
    private String name;

    /**
     * Constructs a new instance of the Genre class.
     *
     * @param id   The ID of the genre.
     * @param name The name of the genre.
     */
    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Other methods as needed...
}