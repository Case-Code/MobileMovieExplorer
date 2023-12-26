package com.casecode.mobilemovieexplorer;

import android.app.Application;

import com.casecode.mobilemovieexplorer.di.components.AppComponent;
import com.casecode.mobilemovieexplorer.di.components.DaggerAppComponent;

public class MobileMovieExplorerApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}

