package com.casecode.mobilemovieexplorer.domain.model.moviesdetails;

import lombok.Data;

/**
 * Class representing a production company associated with a movie.
 */
public record ProductionCompany (
     int id,
     String logoPath,
     String name,
     String originCountry
){

}