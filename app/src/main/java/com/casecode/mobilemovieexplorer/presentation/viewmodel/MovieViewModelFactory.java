package com.casecode.mobilemovieexplorer.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.casecode.mobilemovieexplorer.data.utils.NetworkMonitor;
import com.casecode.mobilemovieexplorer.domain.usecase.MovieUseCase;

import javax.inject.Inject;

/**
 * Created by Mahmoud Abdalhafeez on 12/26/2023
 */
public class MovieViewModelFactory implements ViewModelProvider.Factory {
    private final MovieUseCase mMovieUseCase;
    private final NetworkMonitor networkMonitor;

    @Inject
    public MovieViewModelFactory(MovieUseCase movieUseCase, NetworkMonitor networkMonitor) {
        this.mMovieUseCase = movieUseCase;
        this.networkMonitor = networkMonitor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new MovieViewModel(mMovieUseCase, networkMonitor);
        }
        throw new IllegalArgumentException("UnKnown class name");
    }
}
