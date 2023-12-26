package com.casecode.mobilemovieexplorer.di.modules;

import com.casecode.mobilemovieexplorer.data.api.MovieApiService;
import com.casecode.mobilemovieexplorer.data.repository.MovieRepositoryImpl;
import com.casecode.mobilemovieexplorer.domain.repository.MovieRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public MovieRepository provideMovieRepository(Retrofit retrofit) {
        return new MovieRepositoryImpl(retrofit.create(MovieApiService.class));
    }
}
