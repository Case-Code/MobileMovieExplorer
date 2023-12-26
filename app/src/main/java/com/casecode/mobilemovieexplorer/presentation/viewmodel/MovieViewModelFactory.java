package com.casecode.mobilemovieexplorer.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.casecode.mobilemovieexplorer.domain.usecase.MovieUseCase;

import javax.inject.Inject;

/**
 * Created by Mahmoud Abdalhafeez on 12/26/2023
 */
public class MovieViewModelFactory implements ViewModelProvider.Factory {
    private final MovieUseCase mMovieUseCase;

    @Inject
    public MovieViewModelFactory(MovieUseCase movieUseCase) {
        this.mMovieUseCase = movieUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new MovieViewModel(mMovieUseCase);
        }
        throw new IllegalArgumentException("UnKnown class name");
    }
}
