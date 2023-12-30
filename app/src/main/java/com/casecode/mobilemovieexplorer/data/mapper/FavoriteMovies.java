package com.casecode.mobilemovieexplorer.data.mapper;

import androidx.annotation.NonNull;

import com.casecode.mobilemovieexplorer.domain.model.db.FavoriteMovie;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;

/**
 * Created by Mahmoud Abdalhafeez on 12/27/2023
 */
public class FavoriteMovies {
    public static FavoriteMovie asMoviesDetailsMovie(@NonNull MoviesDetailsResponse moviesDetailsResponse){
        int id = moviesDetailsResponse.getId();
        String backdropPath = moviesDetailsResponse.getBackdropPath();
        String originalTitle = moviesDetailsResponse.getOriginalTitle();
        double voteAverage = moviesDetailsResponse.getVoteAverage();
        int runtime = moviesDetailsResponse.getRuntime();
        return new FavoriteMovie(id, backdropPath, originalTitle, voteAverage, runtime);
    }
    public static FavoriteMovie asDomainDemoDetails(@NonNull DemoDetailsResponse demoDetailsResponse){
        int id = demoDetailsResponse.id();
        String backdropPath = demoDetailsResponse.backdropPath();
        String originalTitle = demoDetailsResponse.originalTitle();
        double voteAverage = demoDetailsResponse.voteAverage();
        int runtime = demoDetailsResponse.runtime();
        return new FavoriteMovie(id, backdropPath, originalTitle, voteAverage, runtime);
    }

}