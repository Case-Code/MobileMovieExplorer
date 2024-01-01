package com.casecode.mobilemovieexplorer.data.source;

import com.casecode.mobilemovieexplorer.data.api.MovieApiService;
import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;
import com.casecode.mobilemovieexplorer.presentation.utils.Resource;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

/**
 * Implementation of the {@link MoviesRemoteDataSource} interface that uses a {@link MovieApiService}
 * to retrieve movie-related data from a remote source.
 */
public class MoviesRemoteDataSourceImpl implements MoviesRemoteDataSource {

    /**
     * The Movie API service used for making remote requests.
     */
    private final MovieApiService mMovieApiService;

    /**
     * Constructs a {@code MoviesRemoteDataSourceImpl} instance.
     *
     * @param apiService The Movie API service for making remote requests.
     */
    @Inject
    public MoviesRemoteDataSourceImpl(MovieApiService apiService) {
        this.mMovieApiService = apiService;
    }

    /**
     * Retrieves a list of movies from the remote data source using the Movie API service.
     *
     * @return A {@link Single} emitting a {@link MoviesResponse}.
     */
    @Override
    public Single<MoviesResponse> getMovies() {
        return mMovieApiService.getMovies();
    }

    @Override
    public Single<Resource<MoviesResponse>> getMovies(int page) {
        return mMovieApiService.getMovies(page).map(Resource::success).onErrorReturn(Resource::error);
    }

    /**
     * Retrieves a list of demo movies from the remote data source using the Movie API service.
     *
     * @return A {@link Single} emitting a {@link DemoResponse}.
     */
    @Override
    public Single<DemoResponse> getDemoMovies() {
        return mMovieApiService.getDemoMovies();
    }

    /**
     * Retrieves details for a specific movie from the remote data source using the Movie API service.
     *
     * @param movieId The ID of the movie.
     * @return A {@link Single} emitting a {@link MoviesDetailsResponse}.
     */
    @Override
    public Single<MoviesDetailsResponse> getMovieDetails(int movieId) {
        return mMovieApiService.getMovieDetails(movieId);
    }

    /**
     * Retrieves details for a specific demo from the remote data source using the Movie API service.
     *
     * @param demoId The ID of the demo.
     * @return A {@link Single} emitting a {@link DemoDetailsResponse}.
     */
    @Override
    public Single<DemoDetailsResponse> getDemoDetails(int demoId) {
        return mMovieApiService.getDemoDetails(demoId);
    }
}
