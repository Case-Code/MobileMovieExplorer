package com.casecode.mobilemovieexplorer.domain.usecase;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagingData;

import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.Movie;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.repository.MovieRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The {@code MovieUseCase} class serves as an intermediary between the presentation layer (or view model)
 * and the {@code MovieRepository}. It encapsulates business logic related to movie-related use cases.
 * <p>
 * This class provides methods for retrieving movie data asynchronously using ReactiveX (RxJava).
 * It relies on the {@code MovieRepository} to interact with the data source.
 * <p>
 * Usage example:
 * ```
 * MovieUseCase movieUseCase = new MovieUseCase(movieRepository);
 * Single<MoviesResponse> moviesSingle = movieUseCase.getMovies();
 * moviesSingle.subscribe(new SingleObserver<MoviesResponse>() {
 *
 * @Override public void onSubscribe(Disposable d) {
 * // Handle subscription
 * }
 * @Override public void onSuccess(MoviesResponse moviesResponse) {
 * // Handle successful response
 * }
 * @Override public void onError(Throwable e) {
 * // Handle error
 * }
 * });
 * ```
 * @see MovieRepository
 */
public class MovieUseCase {

    /**
     * The {@code MovieRepository} instance responsible for providing access to movie-related data.
     */
    private final MovieRepository movieRepository;

    /**
     * Constructs a {@code MovieUseCase} instance with the specified {@code movieRepository}.
     *
     * @param movieRepository The repository providing access to movie-related data.
     */
    @Inject
    public MovieUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Retrieves a list of movies.
     *
     * @return A {@code Single} emitting the response containing a list of movies.
     */
    public Single<MoviesResponse> getMovies() {
        return movieRepository.getMovies();

    }

    public Flowable<PagingData<Movie>> getMoviesPaging() {
        return movieRepository.getMoviesPaging();
    }

    /**
     * Retrieves a list of demo movies.
     *
     * @return A {@code Single} emitting the response containing a list of demo movies.
     */
    public Single<DemoResponse> getDemoMovies() {
        return movieRepository.getDemoMovies();
    }

    /**
     * Retrieves details for a specific movie identified by its ID.
     *
     * @param movieId The ID of the movie.
     * @return A {@code Single} emitting the response containing details of the specified movie.
     */
    public Single<MoviesDetailsResponse> getMovieDetails(int movieId) {
        return movieRepository.getMovieDetails(movieId);
    }

    /**
     * Retrieves details for a specific demo movie identified by its ID.
     *
     * @param demoId The ID of the demo movie.
     * @return A {@code Single} emitting the response containing details of the specified demo movie.
     */
    public Single<DemoDetailsResponse> getDemoDetails(int demoId) {
        return movieRepository.getDemoDetails(demoId);
    }

    /**
     * Enqueues a Retrofit call and updates a {@code MutableLiveData} with the response data.
     *
     * @param call     The Retrofit call to be executed asynchronously.
     * @param liveData The {@code MutableLiveData} to be updated with the response data.
     * @param <T>      The type of the expected response.
     */
    private <T> void enqueueCall(Call<T> call, MutableLiveData<T> liveData) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    liveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                // Handle network error
            }
        });
    }
}
