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
 * Created by Mahmoud Abdalhafeez on 12/25/2023
 */
@Module
@InstallIn(SingletonComponent.class)
public class DataSourceModule
{
    @Provides
    @Singleton
    public MoviesRemoteDataSource provideMoviesRemoteDataSource(MovieApiService apiService) {
        return new MoviesRemoteDataSourceImpl(apiService);
    }
}
