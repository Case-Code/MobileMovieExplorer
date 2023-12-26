package com.casecode.mobilemovieexplorer.domain.usecase;

import androidx.lifecycle.MutableLiveData;

import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.repository.MovieRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieUseCase {

    private final MovieRepository movieRepository;

    @Inject
    public MovieUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Single<MoviesResponse> getMovies() {
        return movieRepository.getMovies();
    }

    public Single<DemoResponse> getDemoMovies() {

        return movieRepository.getDemoMovies();
    }

    public Single<MoviesDetailsResponse> getMovieDetails(int movieId) {

        return movieRepository.getMovieDetails(movieId);
    }

    public Single<DemoDetailsResponse> getDemoDetails(int demoId) {

        return movieRepository.getDemoDetails(demoId);
    }

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
