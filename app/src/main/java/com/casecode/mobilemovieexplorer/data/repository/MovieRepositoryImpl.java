package com.casecode.mobilemovieexplorer.data.repository;

import com.casecode.mobilemovieexplorer.data.source.MoviesRemoteDataSource;
import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.repository.MovieRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class MovieRepositoryImpl implements MovieRepository {

    private final MoviesRemoteDataSource remoteDataSource;

    @Inject
    public MovieRepositoryImpl(MoviesRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }


    @Override
    public Single<MoviesResponse> getMovies() {
        return remoteDataSource.getMovies();
    }

    @Override
    public Single<DemoResponse> getDemoMovies() {
        return remoteDataSource.getDemoMovies();
    }

    @Override
    public Single<MoviesDetailsResponse> getMovieDetails(int movieId) {
        return remoteDataSource.getMovieDetails(movieId);
    }

    @Override
    public Single<DemoDetailsResponse> getDemoDetails(int demoId) {
        return remoteDataSource.getDemoDetails(demoId);
    }
}