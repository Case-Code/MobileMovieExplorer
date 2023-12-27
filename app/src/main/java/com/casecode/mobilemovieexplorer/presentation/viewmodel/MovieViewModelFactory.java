package com.casecode.mobilemovieexplorer.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.casecode.mobilemovieexplorer.data.utils.NetworkMonitor;
import com.casecode.mobilemovieexplorer.domain.usecase.MovieUseCase;

import javax.inject.Inject;

/**
 * Factory class for creating instances of the MovieViewModel.
 * Implements the ViewModelProvider.Factory interface to enable dependency injection
 * when creating the MovieViewModel.
 */
public class MovieViewModelFactory implements ViewModelProvider.Factory {

    private final MovieUseCase mMovieUseCase;
    private final NetworkMonitor networkMonitor;

    /**
     * Constructs a MovieViewModelFactory with the specified dependencies.
     *
     * @param movieUseCase   The MovieUseCase instance used by the MovieViewModel.
     * @param networkMonitor The NetworkMonitor instance used by the MovieViewModel.
     */
    @Inject
    public MovieViewModelFactory(MovieUseCase movieUseCase, NetworkMonitor networkMonitor) {
        this.mMovieUseCase = movieUseCase;
        this.networkMonitor = networkMonitor;
    }

    /**
     * Creates a new instance of the specified ViewModel class.
     *
     * @param modelClass The class of the ViewModel to be created.
     * @param <T>        The type of the ViewModel.
     * @return A new instance of the specified ViewModel class.
     * @throws IllegalArgumentException If the provided ViewModel class is unknown.
     */
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new MovieViewModel(mMovieUseCase, networkMonitor);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}