package com.casecode.mobilemovieexplorer.data.utils;

import javax.inject.Singleton;

import dagger.hilt.android.scopes.ActivityScoped;
import io.reactivex.rxjava3.core.Flowable;

/**
 * Interface defining methods for monitoring network connectivity.
 */
@ActivityScoped
public interface NetworkMonitor {

    /**
     * Returns a {@link Flowable} emitting boolean values indicating whether the device is online.
     *
     * @return A {@link Flowable} emitting {@code true} if online, {@code false} otherwise.
     */
    Flowable<Boolean> isOnline();
}