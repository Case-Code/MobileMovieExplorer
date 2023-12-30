package com.casecode.mobilemovieexplorer.data.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import androidx.core.content.ContextCompat;

import com.casecode.mobilemovieexplorer.di.utils.AppScheduler;
import com.casecode.mobilemovieexplorer.di.utils.AppSchedulers;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Implementation of the {@link NetworkMonitor} interface that monitors the device's network
 * connectivity using the Android ConnectivityManager.
 */
public class ConnectivityManagerNetworkMonitor implements NetworkMonitor {

    /**
     * The tag used for logging.
     */
    private static final String TAG = "ConnectivityManagerNetworkMonitor";

    /**
     * The application context.
     */
    private final Context context;

    private final Scheduler ioScheduler;
    private final Scheduler mainScheduler;

    /**
     * Constructs a {@code ConnectivityManagerNetworkMonitor} instance.
     *
     * @param context The application context.
     */
    @Inject
    public ConnectivityManagerNetworkMonitor(@ApplicationContext Context context,
                                             @AppScheduler(appSchedulers = AppSchedulers.IO) Scheduler ioScheduler,
                                             @AppScheduler(appSchedulers = AppSchedulers.MAIN) Scheduler mainScheduler) {
        this.context = context;
        this.ioScheduler = ioScheduler;
        this.mainScheduler = mainScheduler;
    }

    /**
     * Returns a {@link Flowable} emitting boolean values indicating whether the device is online.
     *
     * @return A {@link Flowable} emitting {@code true} if online, {@code false} otherwise.
     */
    @Override
    public Flowable<Boolean> isOnline() {
        return Flowable.<Boolean>create(emitter -> {

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
                public void onCapabilitiesChanged(
                        Network network,
                        NetworkCapabilities networkCapabilities) {
                    super.onCapabilitiesChanged(network, networkCapabilities);
                    boolean unmetered = networkCapabilities.hasCapability(
                            NetworkCapabilities.NET_CAPABILITY_NOT_METERED);
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
        }, BackpressureStrategy.LATEST).subscribeOn(ioScheduler).observeOn(mainScheduler);
    }

    /**
     * Checks if the device is currently connected to a network with internet capabilities.
     *
     * @param connectivityManager The ConnectivityManager instance.
     * @return {@code true} if the device is connected to the internet, {@code false} otherwise.
     */
    private boolean isCurrentlyConnected(ConnectivityManager connectivityManager) {
        NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(
                connectivityManager.getActiveNetwork());
        return capabilities != null &&
                (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                        capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) &&
                        (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)));
    }
}