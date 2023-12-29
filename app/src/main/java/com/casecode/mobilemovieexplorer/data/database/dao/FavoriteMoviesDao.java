package com.casecode.mobilemovieexplorer.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.casecode.mobilemovieexplorer.domain.model.db.FavoriteMovie;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

/**
 * Created by Mahmoud Abdalhafeez on 12/27/2023
 */
@Dao
public interface FavoriteMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addFavoriteMovie(FavoriteMovie favoriteMovie);

    @Transaction
    @Query("SELECT * FROM favorite_movie")
    Single<List<FavoriteMovie>> getListFavorite();

    @Transaction
    @Query("SELECT * FROM favorite_movie WHERE id_movie = :idMovie")
    Single<List<FavoriteMovie>> getListFavorite(int idMovie);

    @Delete
    Completable deleteFavoriteMovie(FavoriteMovie favoriteMovie);

}
