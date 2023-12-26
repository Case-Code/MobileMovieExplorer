package com.casecode.mobilemovieexplorer.di.modules;

import com.casecode.mobilemovieexplorer.data.api.MovieApiService;
import com.casecode.mobilemovieexplorer.data.repository.MovieRepositoryImpl;
import com.casecode.mobilemovieexplorer.domain.repository.MovieRepository;
import com.casecode.mobilemovieexplorer.domain.usecase.MovieUseCase;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MovieModule {
    @Provides
    MovieRepository provideMovieRepository(MovieApiService movieApiService) {
        return new MovieRepositoryImpl(movieApiService);
    }

    @Provides
    MovieApiService provideMovieApiService() {
        return new Retrofit.Builder()
                .baseUrl("https://app-vpigadas.herokuapp.com/") // Replace with your base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApiService.class);
    }

    @Provides
    MovieUseCase provideMovieUseCase(MovieRepository movieRepository) {
        return new MovieUseCase(movieRepository);
    }
}

