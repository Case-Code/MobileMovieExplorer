package com.casecode.mobilemovieexplorer.data.api;

import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Retrofit service interface for making API requests related to movies.
 */
public interface MovieApiService {

    /**
     * Retrieves a list of movies.
     *
     * @return A {@link Single} emitting a {@link MoviesResponse}.
     */
    @GET("api/movies/")
    Single<MoviesResponse> getMovies();

    @GET("api/movies/")
    Single<MoviesResponse> getMovies(@Query("page") int page);

    /**
     * Retrieves a list of demo movies.
     *
     * @return A {@link Single} emitting a {@link DemoResponse}.
     */
    @GET("api/movies/demo/")
    Single<DemoResponse> getDemoMovies();

    /**
     * Retrieves details for a specific movie.
     *
     * @param movieId The ID of the movie.
     * @return A {@link Single} emitting a {@link MoviesDetailsResponse}.
     */
    @GET("api/movies/{movieId}")
    Single<MoviesDetailsResponse> getMovieDetails(@Path("movieId") int movieId);

    /**
     * Retrieves details for a specific demo.
     *
     * @param demoId The ID of the demo.
     * @return A {@link Single} emitting a {@link DemoDetailsResponse}.
     */
    @GET("api/movies/demo/{demoId}")
    Single<DemoDetailsResponse> getDemoDetails(@Path("demoId") int demoId);
}


