package com.casecode.mobilemovieexplorer.data.source;

import com.casecode.mobilemovieexplorer.data.api.MovieApiService;
import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

/**
 * Created by Mahmoud Abdalhafeez on 12/26/2023
 */
public class MoviesRemoteDataSourceImpl implements MoviesRemoteDataSource {
    private MovieApiService mMovieApiService;

    @Inject
    public MoviesRemoteDataSourceImpl(MovieApiService apiService) {
        this.mMovieApiService = apiService;
    }

    @Override
    public Single<MoviesResponse> getMovies() {
        return mMovieApiService.getMovies();
    }

    @Override
    public Single<DemoResponse> getDemoMovies() {
        return mMovieApiService.getDemoMovies();
    }

    @Override
    public Single<MoviesDetailsResponse> getMovieDetails(int movieId) {
        return mMovieApiService.getMovieDetails(movieId);
    }

    @Override
    public Single<DemoDetailsResponse> getDemoDetails(int demoId) {
        return mMovieApiService.getDemoDetails(demoId);
    }
}
