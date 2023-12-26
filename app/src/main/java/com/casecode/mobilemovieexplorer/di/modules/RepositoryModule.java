package com.casecode.mobilemovieexplorer.di.modules;

import com.casecode.mobilemovieexplorer.data.api.MovieApiService;
import com.casecode.mobilemovieexplorer.data.repository.MovieRepositoryImpl;
import com.casecode.mobilemovieexplorer.data.source.MoviesRemoteDataSource;
import com.casecode.mobilemovieexplorer.domain.repository.MovieRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {
    private RepositoryModule(){
    }

    @Provides
    @Singleton
    public static MovieRepository provideMovieRepository(MoviesRemoteDataSource moviesRemoteDataSource) {
        return new MovieRepositoryImpl(moviesRemoteDataSource);
    }
}
