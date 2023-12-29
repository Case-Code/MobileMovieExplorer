package com.casecode.mobilemovieexplorer.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.casecode.mobilemovieexplorer.data.database.dao.FavoriteMoviesDao;
import com.casecode.mobilemovieexplorer.domain.model.db.FavoriteMovie;

/**
 * Created by Mahmoud Abdalhafeez on 12/27/2023
 */
@Database(entities = {FavoriteMovie.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "mobile_movie_explorer_db";

    public AppDatabase(){}

public abstract FavoriteMoviesDao favoriteMoviesDao ();

}
