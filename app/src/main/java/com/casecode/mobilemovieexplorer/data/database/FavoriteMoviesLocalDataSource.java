package com.casecode.mobilemovieexplorer.data.database;

import com.casecode.mobilemovieexplorer.domain.model.db.FavoriteMovie;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

/**
 * Created by Mahmoud Abdalhafeez on 12/27/2023
 */
public interface FavoriteMoviesLocalDataSource {
    Completable addFavoriteMovie(FavoriteMovie favoriteMovie);

    Single<List<FavoriteMovie>> getListFavorite();

    Single<List<FavoriteMovie>> getListFavorite(int id);

    Completable deleteFavoriteMovie(FavoriteMovie favoriteMovie);

}
