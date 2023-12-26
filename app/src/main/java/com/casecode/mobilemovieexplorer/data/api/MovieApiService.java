package com.casecode.mobilemovieexplorer.data.api;

import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
public interface MovieApiService {
    @Headers("Content-Type: application/json")
    @GET("api/movies/")
    Single<MoviesResponse> getMovies();

    @GET("api/movies/demo/")
    Single<DemoResponse> getDemoMovies();

    @GET("api/movies/{movieId}")
    Single<MoviesDetailsResponse> getMovieDetails(@Path("movieId") int movieId);

    @GET("api/movies/demo/{demoId}")
    Single<DemoDetailsResponse> getDemoDetails(@Path("demoId") int demoId);
}


