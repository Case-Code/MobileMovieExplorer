package com.casecode.mobilemovieexplorer.data.api;

import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface MovieApiService {

    @GET("api/movies/")
    @Headers("Content-Type: application/json")
    Call<MoviesResponse> getMovies();

    @GET("api/movies/demo/")
    @Headers("Content-Type: application/json")
    Call<DemoResponse> getDemoMovies();

    @GET("api/movies/{movieId}")
    @Headers("Content-Type: application/json")
    Call<MoviesDetailsResponse> getMovieDetails(@Path("movieId") int movieId);

    @GET("api/movies/demo/{demoId}")
    @Headers("Content-Type: application/json")
    Call<DemoDetailsResponse> getDemoDetails(@Path("demoId") int demoId);
}


