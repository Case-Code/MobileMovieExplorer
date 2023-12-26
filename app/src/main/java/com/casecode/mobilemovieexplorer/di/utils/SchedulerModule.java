package com.casecode.mobilemovieexplorer.di.utils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by Mahmoud Abdalhafeez on 12/26/2023
 */
@Module
@InstallIn(SingletonComponent.class)
public class SchedulerModule
{

    @Provides
    public Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    public Scheduler provideMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
