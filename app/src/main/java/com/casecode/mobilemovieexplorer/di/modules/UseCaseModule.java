package com.casecode.mobilemovieexplorer.di.modules;

import com.casecode.mobilemovieexplorer.domain.repository.MovieRepository;
import com.casecode.mobilemovieexplorer.domain.usecase.MovieUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

/**
 * Dagger Hilt module that provides use case-related dependencies.
 */
@Module
@InstallIn(SingletonComponent.class)
public class UseCaseModule {

    /**
     * Provides a singleton instance of the {@link MovieUseCase} class.
     *
     * @param movieRepository The {@link MovieRepository} used by the use case.
     * @return A {@link MovieUseCase} instance.
     */
    @Provides
    @Singleton
    public MovieUseCase provideMovieUseCase(MovieRepository movieRepository) {
        return new MovieUseCase(movieRepository);
    }
}