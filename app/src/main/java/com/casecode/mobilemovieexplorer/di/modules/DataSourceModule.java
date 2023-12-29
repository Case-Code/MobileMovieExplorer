package com.casecode.mobilemovieexplorer.di.modules;

import com.casecode.mobilemovieexplorer.data.api.MovieApiService;
import com.casecode.mobilemovieexplorer.data.database.FavoriteMoviesLocalDataSource;
import com.casecode.mobilemovieexplorer.data.database.FavoriteMoviesLocalDataSourceImpl;
import com.casecode.mobilemovieexplorer.data.source.MoviesRemoteDataSource;
import com.casecode.mobilemovieexplorer.data.source.MoviesRemoteDataSourceImpl;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

/**
 * Dagger Hilt module that provides data source dependencies.
 */
@Module
@InstallIn(SingletonComponent.class)
public abstract class DataSourceModule {

    /**
     * Provides a singleton instance of the {@link MoviesRemoteDataSource} interface.
     *
     * @param apiService The {@link MovieApiService} used for making remote requests.
     * @return A {@link MoviesRemoteDataSource} instance.
     */
    @Binds
    public abstract MoviesRemoteDataSource bindMoviesRemoteDataSource(MoviesRemoteDataSourceImpl apiService);

    @Binds
    public abstract  FavoriteMoviesLocalDataSource bindFavoriteMoviesLocalDataSource(FavoriteMoviesLocalDataSourceImpl favoriteMoviesLocalDataSource) ;

}