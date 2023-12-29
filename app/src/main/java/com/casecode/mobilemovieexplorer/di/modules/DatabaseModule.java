package com.casecode.mobilemovieexplorer.di.modules;

import android.content.Context;

import androidx.room.Room;

import com.casecode.mobilemovieexplorer.data.database.AppDatabase;
import com.casecode.mobilemovieexplorer.data.database.dao.FavoriteMoviesDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

/**
 * Created by Mahmoud Abdalhafeez on 12/27/2023
 */
@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public static AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public static FavoriteMoviesDao provideOrderDao(AppDatabase appDatabase) {
        return appDatabase.favoriteMoviesDao();
    }

}
