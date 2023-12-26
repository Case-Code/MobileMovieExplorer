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
 * Created by Mahmoud Abdalhafeez on 12/26/2023
 */
@Module
@InstallIn(SingletonComponent.class)
public class SchedulerModule
{

    @Provides
    @AppScheduler(appSchedulers = AppSchedulers.IO)
    public Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @AppScheduler(appSchedulers = AppSchedulers.MAIN)
    public Scheduler provideMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
