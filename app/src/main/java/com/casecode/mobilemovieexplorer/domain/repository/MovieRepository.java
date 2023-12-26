package com.casecode.mobilemovieexplorer.domain.repository;

import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;

public interface MovieRepository {

    Single<MoviesResponse> getMovies();

    Single<DemoResponse> getDemoMovies();

    Single<MoviesDetailsResponse> getMovieDetails(int movieId);

    Single<DemoDetailsResponse> getDemoDetails(int demoId);
}