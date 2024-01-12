package com.casecode.mobilemovieexplorer.data.utils;

import io.reactivex.rxjava3.core.Flowable;

/**
 * Interface defining methods for monitoring network connectivity.
 */
public interface NetworkMonitor {


    Flowable<Boolean> isOnline();
}