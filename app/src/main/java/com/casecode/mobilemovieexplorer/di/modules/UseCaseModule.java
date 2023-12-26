package com.casecode.mobilemovieexplorer.di.modules;

import com.casecode.mobilemovieexplorer.domain.repository.MovieRepository;
import com.casecode.mobilemovieexplorer.domain.usecase.MovieUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)

public class UseCaseModule {

    @Provides
    @Singleton
    public MovieUseCase provideMovieUseCase(MovieRepository movieRepository) {
        return new MovieUseCase(movieRepository);
    }
}

