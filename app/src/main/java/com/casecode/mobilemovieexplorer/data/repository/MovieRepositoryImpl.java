package com.casecode.mobilemovieexplorer.data.repository;

import com.casecode.mobilemovieexplorer.data.source.MoviesRemoteDataSource;
import com.casecode.mobilemovieexplorer.di.utils.AppScheduler;
import com.casecode.mobilemovieexplorer.di.utils.AppSchedulers;
import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.repository.MovieRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieRepositoryImpl implements MovieRepository {

    private final MoviesRemoteDataSource remoteDataSource;
    private final Scheduler ioScheduler;
    private final Scheduler mainScheduler;

    @Inject
    public MovieRepositoryImpl(MoviesRemoteDataSource remoteDataSource,
                               @AppScheduler(appSchedulers = AppSchedulers.IO)Scheduler ioScheduler,
                               @AppScheduler(appSchedulers = AppSchedulers.MAIN)Scheduler mainScheduler) {
        this.remoteDataSource = remoteDataSource;
        this.ioScheduler = ioScheduler;
        this.mainScheduler = mainScheduler;
    }


    @Override
    public Single<MoviesResponse> getMovies() {
        return remoteDataSource.getMovies().subscribeOn(ioScheduler)
                .observeOn(mainScheduler);
    }

    @Override
    public Single<DemoResponse> getDemoMovies() {
        return remoteDataSource.getDemoMovies().subscribeOn(ioScheduler)
                .observeOn(mainScheduler);
    }

    @Override
    public Single<MoviesDetailsResponse> getMovieDetails(int movieId) {
        return remoteDataSource.getMovieDetails(movieId).subscribeOn(ioScheduler)
                .observeOn(mainScheduler);
    }

    @Override
    public Single<DemoDetailsResponse> getDemoDetails(int demoId) {
        return remoteDataSource.getDemoDetails(demoId).subscribeOn(ioScheduler)
                .observeOn(mainScheduler);
    }
}