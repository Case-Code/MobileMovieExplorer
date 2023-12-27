package com.casecode.mobilemovieexplorer.presentation;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;
import timber.log.Timber;


/**
 * Custom Application class for the Mobile Movie Explorer app.
 * This class is annotated with @HiltAndroidApp to enable Hilt for dependency injection.
 */
@HiltAndroidApp
public class MobileMovieExplorerApplication extends Application {

    /**
     * Called when the application is starting. Used to initialize some global application state.
     * This method is called before the first activity is displayed.
     */
    @Override
    public void onCreate() {
        super.onCreate();

        // Plant a Timber DebugTree for logging during development.
        Timber.plant(new Timber.DebugTree());
    }

}
