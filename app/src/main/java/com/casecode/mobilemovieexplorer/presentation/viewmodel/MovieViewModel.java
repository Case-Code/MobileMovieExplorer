package com.casecode.mobilemovieexplorer.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.repository.MovieRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {

    private final MovieRepository movieRepository;

    private final MutableLiveData<MoviesResponse> moviesLiveData = new MutableLiveData<>();
    private final MutableLiveData<DemoResponse> demoMoviesLiveData = new MutableLiveData<>();
    private final MutableLiveData<MoviesDetailsResponse> movieDetailsLiveData = new MutableLiveData<>();
    private final MutableLiveData<DemoDetailsResponse> demoDetailsLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    public MovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void fetchMovies() {
        Call<MoviesResponse> call = movieRepository.getMovies();
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    moviesLiveData.setValue(response.body());
                } else {
                    errorLiveData.setValue("Failed to fetch movies");
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                errorLiveData.setValue("Network error");
            }
        });
    }

    public void fetchDemoMovies() {
        Call<DemoResponse> call = movieRepository.getDemoMovies();
        call.enqueue(new Callback<DemoResponse>() {
            @Override
            public void onResponse(Call<DemoResponse> call, Response<DemoResponse> response) {
                if (response.isSuccessful()) {
                    demoMoviesLiveData.setValue(response.body());
                } else {
                    errorLiveData.setValue("Failed to fetch demo movies");
                }
            }

            @Override
            public void onFailure(Call<DemoResponse> call, Throwable t) {
                errorLiveData.setValue("Network error");
            }
        });
    }

    public void fetchMovieDetails(int movieId) {
        Call<MoviesDetailsResponse> call = movieRepository.getMovieDetails(movieId);
        call.enqueue(new Callback<MoviesDetailsResponse>() {
            @Override
            public void onResponse(Call<MoviesDetailsResponse> call, Response<MoviesDetailsResponse> response) {
                if (response.isSuccessful()) {
                    movieDetailsLiveData.setValue(response.body());
                } else {
                    errorLiveData.setValue("Failed to fetch movie details");
                }
            }

            @Override
            public void onFailure(Call<MoviesDetailsResponse> call, Throwable t) {
                errorLiveData.setValue("Network error");
            }
        });
    }

    public void fetchDemoDetails(int demoId) {
        Call<DemoDetailsResponse> call = movieRepository.getDemoDetails(demoId);
        call.enqueue(new Callback<DemoDetailsResponse>() {
            @Override
            public void onResponse(Call<DemoDetailsResponse> call, Response<DemoDetailsResponse> response) {
                if (response.isSuccessful()) {
                    demoDetailsLiveData.setValue(response.body());
                } else {
                    errorLiveData.setValue("Failed to fetch demo details");
                }
            }

            @Override
            public void onFailure(Call<DemoDetailsResponse> call, Throwable t) {
                errorLiveData.setValue("Network error");
            }
        });
    }

    public LiveData<MoviesResponse> getMoviesLiveData() {
        return moviesLiveData;
    }

    public LiveData<DemoResponse> getDemoMoviesLiveData() {
        return demoMoviesLiveData;
    }

    public LiveData<MoviesDetailsResponse> getMovieDetailsLiveData() {
        return movieDetailsLiveData;
    }

    public LiveData<DemoDetailsResponse> getDemoDetailsLiveData() {
        return demoDetailsLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }
}


