package com.casecode.mobilemovieexplorer.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.repository.MovieRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.repository.MovieRepository;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieUseCase {

    private final MovieRepository movieRepository;

    @Inject
    public MovieUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<MoviesResponse> getMovies() {
        MutableLiveData<MoviesResponse> moviesLiveData = new MutableLiveData<>();
        Call<MoviesResponse> call = movieRepository.getMovies();
        enqueueCall(call, moviesLiveData);
        return moviesLiveData;
    }

    public LiveData<DemoResponse> getDemoMovies() {
        MutableLiveData<DemoResponse> demoMoviesLiveData = new MutableLiveData<>();
        Call<DemoResponse> call = movieRepository.getDemoMovies();
        enqueueCall(call, demoMoviesLiveData);
        return demoMoviesLiveData;
    }

    public LiveData<MoviesDetailsResponse> getMovieDetails(int movieId) {
        MutableLiveData<MoviesDetailsResponse> movieDetailsLiveData = new MutableLiveData<>();
        Call<MoviesDetailsResponse> call = movieRepository.getMovieDetails(movieId);
        enqueueCall(call, movieDetailsLiveData);
        return movieDetailsLiveData;
    }

    public LiveData<DemoDetailsResponse> getDemoDetails(int demoId) {
        MutableLiveData<DemoDetailsResponse> demoDetailsLiveData = new MutableLiveData<>();
        Call<DemoDetailsResponse> call = movieRepository.getDemoDetails(demoId);
        enqueueCall(call, demoDetailsLiveData);
        return demoDetailsLiveData;
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
