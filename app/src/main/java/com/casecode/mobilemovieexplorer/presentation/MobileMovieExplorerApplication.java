package com.casecode.mobilemovieexplorer.presentation;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;
import timber.log.Timber;


@HiltAndroidApp
public class MobileMovieExplorerApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

    }

}

