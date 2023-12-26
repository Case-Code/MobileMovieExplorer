package com.casecode.mobilemovieexplorer.di.components;

import com.casecode.mobilemovieexplorer.MainActivity;
import com.casecode.mobilemovieexplorer.di.modules.NetworkModule;
import com.casecode.mobilemovieexplorer.di.modules.RepositoryModule;
import com.casecode.mobilemovieexplorer.di.modules.UseCaseModule;
import com.casecode.mobilemovieexplorer.di.modules.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        NetworkModule.class,
        RepositoryModule.class,
        UseCaseModule.class,
        ViewModelModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    // Add more injection methods if needed
}