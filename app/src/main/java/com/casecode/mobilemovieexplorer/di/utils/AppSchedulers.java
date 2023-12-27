package com.casecode.mobilemovieexplorer.di.utils;

/**
 * Enum representing different types of schedulers for Dagger Hilt annotations.
 */
public enum AppSchedulers {

    /**
     * IO scheduler for background tasks.
     */
    IO,

    /**
     * Main thread scheduler for UI-related tasks.
     */
    MAIN
}