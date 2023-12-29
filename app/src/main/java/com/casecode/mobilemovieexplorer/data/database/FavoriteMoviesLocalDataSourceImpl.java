package com.casecode.mobilemovieexplorer.data.database;

import com.casecode.mobilemovieexplorer.data.database.dao.FavoriteMoviesDao;
import com.casecode.mobilemovieexplorer.domain.model.db.FavoriteMovie;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

/**
 * Created by Mahmoud Abdalhafeez on 12/27/2023
 */

public class FavoriteMoviesLocalDataSourceImpl implements FavoriteMoviesLocalDataSource {
    private final FavoriteMoviesDao mFavoriteMoviesDao;

    @Inject
    public FavoriteMoviesLocalDataSourceImpl(FavoriteMoviesDao favoriteMoviesDao) {
        this.mFavoriteMoviesDao = favoriteMoviesDao;
    }

    @Override
    public Completable addFavoriteMovie(FavoriteMovie favoriteMovie) {
        return mFavoriteMoviesDao.addFavoriteMovie(favoriteMovie);
    }

    @Override
    public Single<List<FavoriteMovie>> getListFavorite() {
        return mFavoriteMoviesDao.getListFavorite();
    }

    @Override
    public Single<List<FavoriteMovie>> getListFavorite(int id) {
        return mFavoriteMoviesDao.getListFavorite(id);
    }

    @Override
    public Completable deleteFavoriteMovie(FavoriteMovie favoriteMovie) {
        return mFavoriteMoviesDao.deleteFavoriteMovie(favoriteMovie);
    }
}
