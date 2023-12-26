package com.casecode.mobilemovieexplorer.data.utils;

import io.reactivex.rxjava3.core.Flowable;

/**
 * Created by Mahmoud Abdalhafeez on 12/26/2023
 */
public interface NetworkMonitor {
    Flowable<Boolean> isOnline();

}
