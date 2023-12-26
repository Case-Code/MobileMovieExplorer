package com.casecode.mobilemovieexplorer.domain.repository;

import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;

import retrofit2.Call;

public interface MovieRepository {

    Call<MoviesResponse> getMovies();

    Call<DemoResponse> getDemoMovies();

    Call<MoviesDetailsResponse> getMovieDetails(int movieId);

    Call<DemoDetailsResponse> getDemoDetails(int demoId);
}