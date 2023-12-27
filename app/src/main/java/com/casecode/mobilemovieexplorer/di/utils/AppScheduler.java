package com.casecode.mobilemovieexplorer.di.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;


/**
 * Dagger Hilt qualifier annotation to specify the type of scheduler (IO or MAIN).
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScheduler {

    /**
     * Returns the type of scheduler.
     *
     * @return The type of scheduler.
     */
    AppSchedulers appSchedulers();
}