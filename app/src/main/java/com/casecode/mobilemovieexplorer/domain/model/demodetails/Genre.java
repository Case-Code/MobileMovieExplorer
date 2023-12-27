package com.casecode.mobilemovieexplorer.domain.model.demodetails;

/**
 * Record representing a movie genre.
 *
 * @param id   The ID of the genre.
 * @param name The name of the genre.
 */
public record Genre(
        int id,
        String name
) {
}