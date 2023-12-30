package com.casecode.mobilemovieexplorer.data.repository;

import com.casecode.mobilemovieexplorer.data.database.FavoriteMoviesLocalDataSource;
import com.casecode.mobilemovieexplorer.di.utils.AppScheduler;
import com.casecode.mobilemovieexplorer.di.utils.AppSchedulers;
import com.casecode.mobilemovieexplorer.domain.model.db.FavoriteMovie;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;

/**
 * Created by Mahmoud Abdalhafeez on 12/27/2023
 */
@Singleton
public class FavoriteMoviesRepository {
    private final FavoriteMoviesLocalDataSource mFavoriteMoviesLocalDataSource;
    private final Scheduler ioScheduler;
    private final Scheduler mainScheduler;

    @Inject
    public FavoriteMoviesRepository(FavoriteMoviesLocalDataSource favoriteMoviesLocalDataSource,
                                    @AppScheduler(appSchedulers = AppSchedulers.IO) Scheduler ioScheduler,
                                    @AppScheduler(appSchedulers = AppSchedulers.MAIN) Scheduler mainScheduler) {
        this.mFavoriteMoviesLocalDataSource = favoriteMoviesLocalDataSource;
        this.ioScheduler = ioScheduler;
        this.mainScheduler = mainScheduler;
    }

    public Completable addFavoriteMovie(FavoriteMovie favoriteMovie) {
        return mFavoriteMoviesLocalDataSource.addFavoriteMovie(favoriteMovie)
                .subscribeOn(ioScheduler).observeOn(mainScheduler);
    }

    public Single<List<FavoriteMovie>> getListFavorite() {
        return mFavoriteMoviesLocalDataSource.getListFavorite().subscribeOn(ioScheduler)
                .observeOn(mainScheduler);
    }

    public Single<List<FavoriteMovie>> getListFavorite(int id) {
        return mFavoriteMoviesLocalDataSource.getListFavorite(id).subscribeOn(ioScheduler)
                .observeOn(mainScheduler);
    }

    public Completable deleteFavoriteMovie(FavoriteMovie favoriteMovie) {
        return mFavoriteMoviesLocalDataSource.deleteFavoriteMovie(favoriteMovie).subscribeOn(ioScheduler).observeOn(mainScheduler);
    }


}
