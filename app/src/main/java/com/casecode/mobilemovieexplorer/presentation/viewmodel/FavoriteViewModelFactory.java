package com.casecode.mobilemovieexplorer.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.casecode.mobilemovieexplorer.data.repository.FavoriteMoviesRepository;

import javax.inject.Inject;

/**
 * Created by Mahmoud Abdalhafeez on 12/27/2023
 */
public class FavoriteViewModelFactory implements ViewModelProvider.Factory {


    private final FavoriteMoviesRepository favoriteMoviesRepository;

    @Inject
    public FavoriteViewModelFactory(FavoriteMoviesRepository favoriteMoviesRepository) {
        this.favoriteMoviesRepository = favoriteMoviesRepository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new FavoriteViewModel(favoriteMoviesRepository);
        }
        throw new IllegalArgumentException("Unknown class name: FavoriteViewModel");
    }
}
