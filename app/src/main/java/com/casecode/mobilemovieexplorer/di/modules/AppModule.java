package com.casecode.mobilemovieexplorer.di.modules;

import android.content.Context;

import com.casecode.mobilemovieexplorer.data.utils.ConnectivityManagerNetworkMonitor;
import com.casecode.mobilemovieexplorer.data.utils.NetworkMonitor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

/**
 * Created by Mahmoud Abdalhafeez on 12/26/2023
 */
@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public NetworkMonitor provideConnectivityManagerNetworkMonitor(@ApplicationContext Context context) {
        return new ConnectivityManagerNetworkMonitor(context);
    }

}
