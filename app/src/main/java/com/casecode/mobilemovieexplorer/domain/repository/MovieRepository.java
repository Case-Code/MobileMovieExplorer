package com.casecode.mobilemovieexplorer.domain.repository;

import androidx.paging.PagingData;

import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.Movie;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

/**
 * Repository interface for retrieving movie-related data.
 */
public interface MovieRepository {

    /**
     * Retrieves a list of movies.
     *
     * @return A Single emitting the response containing a list of movies.
     */
    Single<MoviesResponse> getMovies();

    Flowable<PagingData<Movie>> getMoviesPaging();

    /**
     * Retrieves a list of demo movies.
     *
     * @return A Single emitting the response containing a list of demo movies.
     */
    Single<DemoResponse> getDemoMovies();

    /**
     * Retrieves details for a specific movie.
     *
     * @param movieId The ID of the movie to retrieve details for.
     * @return A Single emitting the response containing details for the specified movie.
     */
    Single<MoviesDetailsResponse> getMovieDetails(int movieId);

    /**
     * Retrieves details for a specific demo.
     *
     * @param demoId The ID of the demo to retrieve details for.
     * @return A Single emitting the response containing details for the specified demo.
     */
    Single<DemoDetailsResponse> getDemoDetails(int demoId);
}