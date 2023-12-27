package com.casecode.mobilemovieexplorer.di.modules;

import com.casecode.mobilemovieexplorer.data.repository.MovieRepositoryImpl;
import com.casecode.mobilemovieexplorer.data.source.MoviesRemoteDataSource;
import com.casecode.mobilemovieexplorer.di.utils.AppScheduler;
import com.casecode.mobilemovieexplorer.di.utils.AppSchedulers;
import com.casecode.mobilemovieexplorer.domain.repository.MovieRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.rxjava3.core.Scheduler;

/**
 * Dagger Hilt module that provides repository-related dependencies.
 */
@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {

    /**
     * Private constructor to prevent instantiation.
     */
    private RepositoryModule() {
    }

    /**
     * Provides a singleton instance of the {@link MovieRepository} interface.
     *
     * @param moviesRemoteDataSource The {@link MoviesRemoteDataSource} used for accessing remote data.
     * @param ioScheduler            The IO scheduler for background tasks.
     * @param mainScheduler          The main scheduler for UI-related tasks.
     * @return A {@link MovieRepository} instance.
     */
    @Provides
    @Singleton
    public static MovieRepository provideMovieRepository(
            MoviesRemoteDataSource moviesRemoteDataSource,
            @AppScheduler(appSchedulers = AppSchedulers.IO) Scheduler ioScheduler,
            @AppScheduler(appSchedulers = AppSchedulers.MAIN) Scheduler mainScheduler) {
        return new MovieRepositoryImpl(moviesRemoteDataSource, ioScheduler, mainScheduler);
    }
}