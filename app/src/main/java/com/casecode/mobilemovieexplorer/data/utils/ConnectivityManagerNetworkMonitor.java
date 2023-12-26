package com.casecode.mobilemovieexplorer.data.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import androidx.core.content.ContextCompat;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import timber.log.Timber;

/**
 * Created by Mahmoud Abdalhafeez on 12/26/2023
 */
public class ConnectivityManagerNetworkMonitor implements NetworkMonitor{
    private static final String TAG = "ConnectivityManagerNetworkMonitor";
    private final Context context;

    @Inject
    public ConnectivityManagerNetworkMonitor(@ApplicationContext Context context) {
        this.context = context;
    }

    @Override
    public Flowable<Boolean> isOnline() {
        return Flowable.create(emitter -> {
            ConnectivityManager connectivityManager = ContextCompat.getSystemService(context, ConnectivityManager.class);
            if (connectivityManager == null) {
                emitter.onNext(false);
                emitter.onComplete();
                return;
            }

            ConnectivityManager.NetworkCallback callback = new ConnectivityManager.NetworkCallback() {
                private final Set<Network> networks = new HashSet<>();

                @Override
                public void onAvailable(Network network) {
                    networks.add(network);
                    emitter.onNext(true);
                }

                @Override
                public void onLost(Network network) {
                    networks.remove(network);
                    emitter.onNext(!networks.isEmpty());
                }

                @Override
                public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                    super.onCapabilitiesChanged(network, networkCapabilities);
                    boolean unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED);
                    Timber.tag(TAG).d("onCapabilitiesChanged = %s", unmetered);
                }
            };

            NetworkRequest request = new NetworkRequest.Builder()
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                    .build();

            connectivityManager.registerNetworkCallback(request, callback);

            emitter.onNext(isCurrentlyConnected(connectivityManager));

            emitter.setCancellable(() -> {
                connectivityManager.unregisterNetworkCallback(callback);
                Timber.d("close connectivity manager");
            });
        }, BackpressureStrategy.LATEST);
    }

    private boolean isCurrentlyConnected(ConnectivityManager connectivityManager) {
        NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        return capabilities != null &&
                (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                        capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) &&
                        (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)));
    }
}

