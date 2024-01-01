package com.casecode.mobilemovieexplorer.data.repository;

import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;
import androidx.paging.rxjava3.RxPagingSource;

import com.casecode.mobilemovieexplorer.data.source.MoviesRemoteDataSource;
import com.casecode.mobilemovieexplorer.di.utils.AppScheduler;
import com.casecode.mobilemovieexplorer.di.utils.AppSchedulers;
import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.Movie;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.repository.MovieRepository;
import com.casecode.mobilemovieexplorer.paging.MoviePagingSource;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Implementation of the {@link MovieRepository} interface that retrieves movie-related data from a
 * remote data source.
 */
public class MovieRepositoryImpl implements MovieRepository {

    private final MoviesRemoteDataSource remoteDataSource;
    private final Scheduler ioScheduler;
    private final Scheduler mainScheduler;

    /**
     * Constructs a {@code MovieRepositoryImpl} instance.
     *
     * @param remoteDataSource The remote data source for movies.
     * @param ioScheduler      The scheduler for IO operations.
     * @param mainScheduler    The scheduler for main thread operations.
     */
    @Inject
    public MovieRepositoryImpl(
            MoviesRemoteDataSource remoteDataSource,
            @AppScheduler(appSchedulers = AppSchedulers.IO) Scheduler ioScheduler,
            @AppScheduler(appSchedulers = AppSchedulers.MAIN) Scheduler mainScheduler) {
        this.remoteDataSource = remoteDataSource;
        this.ioScheduler = ioScheduler;
        this.mainScheduler = mainScheduler;
    }

    /**
     * Retrieves a list of movies from the remote data source.
     *
     * @return A {@link Single} emitting a {@link MoviesResponse}.
     */
    @Override
    public Single<MoviesResponse> getMovies() {
        return remoteDataSource.getMovies().subscribeOn(ioScheduler)
                .observeOn(mainScheduler);
    }

    @Override
    public Flowable<PagingData<Movie>> getMoviesPaging() {

        Pager<Integer, Movie> pager =
                new Pager<>(new PagingConfig(20)
                ,() -> new  MoviePagingSource(remoteDataSource));


        return PagingRx.getFlowable(pager).subscribeOn(ioScheduler)
               .observeOn(mainScheduler);
    }

    /**
     * Retrieves a list of demo movies from the remote data source.
     *
     * @return A {@link Single} emitting a {@link DemoResponse}.
     */
    @Override
    public Single<DemoResponse> getDemoMovies() {
        return remoteDataSource.getDemoMovies().subscribeOn(ioScheduler)
                .observeOn(mainScheduler);
    }

    /**
     * Retrieves details for a specific movie from the remote data source.
     *
     * @param movieId The ID of the movie.
     * @return A {@link Single} emitting a {@link MoviesDetailsResponse}.
     */
    @Override
    public Single<MoviesDetailsResponse> getMovieDetails(int movieId) {
        return remoteDataSource.getMovieDetails(movieId).subscribeOn(ioScheduler)
                .observeOn(mainScheduler);
    }

    /**
     * Retrieves details for a specific demo from the remote data source.
     *
     * @param demoId The ID of the demo.
     * @return A {@link Single} emitting a {@link DemoDetailsResponse}.
     */
    @Override
    public Single<DemoDetailsResponse> getDemoDetails(int demoId) {
        return remoteDataSource.getDemoDetails(demoId).subscribeOn(ioScheduler)
                .observeOn(mainScheduler);
    }
}