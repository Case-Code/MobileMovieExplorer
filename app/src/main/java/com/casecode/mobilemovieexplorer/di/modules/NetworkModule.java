package com.casecode.mobilemovieexplorer.di.modules;

import com.casecode.mobilemovieexplorer.data.api.MovieApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger Hilt module that provides network-related dependencies.
 */
@Module
@InstallIn(SingletonComponent.class)
public abstract class NetworkModule {

    /**
     * The base URL for the API. Replace it with your actual base URL.
     */
    private static final String BASE_URL = "https://app-vpigadas.herokuapp.com/";

    /**
     * Private constructor to prevent instantiation.
     */
    private NetworkModule() {
    }

    /**
     * Provides a singleton instance of {@link OkHttpClient} for making HTTP requests.
     *
     * @return An {@link OkHttpClient} instance.
     */
    @Provides
    @Singleton
    public static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient().newBuilder()
                .addInterceptor(chain -> {
                    Request originalRequest = chain.request();
                    Request.Builder requestBuilder = originalRequest.newBuilder()
                            .header("Content-Type", "application/json");
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Provides a singleton instance of {@link Retrofit} for handling API requests.
     *
     * @param okHttpClient The {@link OkHttpClient} instance.
     * @return A {@link Retrofit} instance.
     */
    @Provides
    @Singleton
    public static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    /**
     * Provides a singleton instance of the {@link MovieApiService} interface for making API calls.
     *
     * @param retrofit The {@link Retrofit} instance.
     * @return A {@link MovieApiService} instance.
     */
    @Provides
    @Singleton
    public static MovieApiService provideMovieApiService(Retrofit retrofit) {
        return retrofit.create(MovieApiService.class);
    }
}