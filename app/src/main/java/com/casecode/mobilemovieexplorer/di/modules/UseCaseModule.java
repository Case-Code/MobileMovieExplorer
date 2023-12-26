package com.casecode.mobilemovieexplorer.di.modules;

import com.casecode.mobilemovieexplorer.domain.repository.MovieRepository;
import com.casecode.mobilemovieexplorer.domain.usecase.MovieUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

    @Provides
    @Singleton
    public MovieUseCase provideMovieUseCase(MovieRepository movieRepository) {
        return new MovieUseCase(movieRepository);
    }
}

