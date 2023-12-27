package com.casecode.mobilemovieexplorer.di.modules;


import com.casecode.mobilemovieexplorer.di.utils.AppScheduler;
import com.casecode.mobilemovieexplorer.di.utils.AppSchedulers;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Dagger Hilt module that provides scheduler-related dependencies.
 */
@Module
@InstallIn(SingletonComponent.class)
public class SchedulerModule {

    /**
     * Provides a scheduler for IO-bound tasks.
     *
     * @return The {@link Scheduler} for IO-bound tasks.
     */
    @Provides
    @AppScheduler(appSchedulers = AppSchedulers.IO)
    public Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    /**
     * Provides the main thread scheduler for UI-related tasks.
     *
     * @return The {@link Scheduler} for UI-related tasks.
     */
    @Provides
    @AppScheduler(appSchedulers = AppSchedulers.MAIN)
    public Scheduler provideMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }
}