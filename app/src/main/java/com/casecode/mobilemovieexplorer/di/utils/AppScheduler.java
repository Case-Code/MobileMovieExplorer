package com.casecode.mobilemovieexplorer.di.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;


@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScheduler {
    AppSchedulers appSchedulers();
}

