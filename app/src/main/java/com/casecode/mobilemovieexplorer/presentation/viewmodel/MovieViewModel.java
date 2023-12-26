package com.casecode.mobilemovieexplorer.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.casecode.mobilemovieexplorer.MyApplication;
import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.usecase.MovieUseCase;

import javax.inject.Inject;

public class MovieViewModel extends ViewModel {

    private final MovieUseCase movieUseCase;

    @Inject
    public MovieViewModel(MovieUseCase movieUseCase) {
        this.movieUseCase = movieUseCase;
    }

    public LiveData<MoviesResponse> getMovies() {
        return movieUseCase.getMovies();
    }

    public LiveData<DemoResponse> getDemoMovies() {
        return movieUseCase.getDemoMovies();
    }

    public LiveData<MoviesDetailsResponse> getMovieDetails(int movieId) {
        return movieUseCase.getMovieDetails(movieId);
    }

    public LiveData<DemoDetailsResponse> getDemoDetails(int demoId) {
        return movieUseCase.getDemoDetails(demoId);
    }
}


