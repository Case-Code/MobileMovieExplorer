package com.casecode.mobilemovieexplorer.data.source;

import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;

import io.reactivex.rxjava3.core.Single;

/**
 * Created by Mahmoud Abdalhafeez on 12/26/2023
 */
public interface MoviesRemoteDataSource {
    Single<MoviesResponse> getMovies();

    Single<DemoResponse> getDemoMovies();

    Single<MoviesDetailsResponse> getMovieDetails(int movieId);

    Single<DemoDetailsResponse> getDemoDetails(int demoId);
}
