package com.casecode.mobilemovieexplorer.di.modules;

import com.casecode.mobilemovieexplorer.domain.usecase.MovieUseCase;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {

    @Provides
    @Singleton
    MovieViewModel provideMovieViewModel(MovieUseCase movieUseCase) {
        return new MovieViewModel(movieUseCase);
    }
}