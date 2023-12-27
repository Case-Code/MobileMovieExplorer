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
 * Dagger Hilt module that provides application-wide dependencies.
 */
@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    /**
     * Provides a singleton instance of the {@link NetworkMonitor} interface.
     *
     * @param context The application context.
     * @return A {@link NetworkMonitor} instance.
     */
    @Provides
    @Singleton
    public NetworkMonitor provideConnectivityManagerNetworkMonitor(@ApplicationContext Context context) {
        return new ConnectivityManagerNetworkMonitor(context);
    }
}