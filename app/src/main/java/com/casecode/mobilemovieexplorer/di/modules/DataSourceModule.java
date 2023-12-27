package com.casecode.mobilemovieexplorer.di.modules;

import com.casecode.mobilemovieexplorer.data.api.MovieApiService;
import com.casecode.mobilemovieexplorer.data.source.MoviesRemoteDataSource;
import com.casecode.mobilemovieexplorer.data.source.MoviesRemoteDataSourceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

/**
 * Dagger Hilt module that provides data source dependencies.
 */
@Module
@InstallIn(SingletonComponent.class)
public class DataSourceModule {

    /**
     * Provides a singleton instance of the {@link MoviesRemoteDataSource} interface.
     *
     * @param apiService The {@link MovieApiService} used for making remote requests.
     * @return A {@link MoviesRemoteDataSource} instance.
     */
    @Provides
    @Singleton
    public MoviesRemoteDataSource provideMoviesRemoteDataSource(MovieApiService apiService) {
        return new MoviesRemoteDataSourceImpl(apiService);
    }
}