package com.casecode.mobilemovieexplorer.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://app-vpigadas.herokuapp.com/") // Replace with your base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}