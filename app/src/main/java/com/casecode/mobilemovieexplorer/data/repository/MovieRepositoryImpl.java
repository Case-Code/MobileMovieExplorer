package com.casecode.mobilemovieexplorer.data.repository;

import com.casecode.mobilemovieexplorer.data.api.MovieApiService;
import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.repository.MovieRepository;

import javax.inject.Inject;

import retrofit2.Call;

public class MovieRepositoryImpl implements MovieRepository {

    private final MovieApiService movieApiService;

    @Inject
    public MovieRepositoryImpl(MovieApiService movieApiService) {
        this.movieApiService = movieApiService;
    }

    @Override
    public Call<MoviesResponse> getMovies() {
        return movieApiService.getMovies();
    }

    @Override
    public Call<DemoResponse> getDemoMovies() {
        return movieApiService.getDemoMovies();
    }

    @Override
    public Call<MoviesDetailsResponse> getMovieDetails(int movieId) {
        return movieApiService.getMovieDetails(movieId);
    }

    @Override
    public Call<DemoDetailsResponse> getDemoDetails(int demoId) {
        return movieApiService.getDemoDetails(demoId);
    }
}