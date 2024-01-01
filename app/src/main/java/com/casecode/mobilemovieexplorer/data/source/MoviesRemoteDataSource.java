package com.casecode.mobilemovieexplorer.data.source;

import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;
import com.casecode.mobilemovieexplorer.presentation.utils.Resource;

import io.reactivex.rxjava3.core.Single;

/**
 * Interface defining methods for retrieving movie-related data from a remote data source.
 */
public interface MoviesRemoteDataSource {

    /**
     * Retrieves a list of movies from the remote data source.
     *
     * @return A {@link Single} emitting a {@link MoviesResponse}.
     */
    Single<MoviesResponse> getMovies();


    Single<Resource<MoviesResponse>> getMovies(int page);

    /**
     * Retrieves a list of demo movies from the remote data source.
     *
     * @return A {@link Single} emitting a {@link DemoResponse}.
     */
    Single<DemoResponse> getDemoMovies();

    /**
     * Retrieves details for a specific movie from the remote data source.
     *
     * @param movieId The ID of the movie.
     * @return A {@link Single} emitting a {@link MoviesDetailsResponse}.
     */
    Single<MoviesDetailsResponse> getMovieDetails(int movieId);

    /**
     * Retrieves details for a specific demo from the remote data source.
     *
     * @param demoId The ID of the demo.
     * @return A {@link Single} emitting a {@link DemoDetailsResponse}.
     */
    Single<DemoDetailsResponse> getDemoDetails(int demoId);
}
