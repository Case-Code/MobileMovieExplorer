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

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {
    private RepositoryModule() {
    }

    @Provides
    @Singleton
    public static MovieRepository provideMovieRepository(MoviesRemoteDataSource moviesRemoteDataSource,
                                                         @AppScheduler(appSchedulers = AppSchedulers.IO) Scheduler ioScheduler,
                                                         @AppScheduler(appSchedulers = AppSchedulers.MAIN) Scheduler mainScheduler) {
        return new MovieRepositoryImpl(moviesRemoteDataSource, ioScheduler, mainScheduler);
    }
}
