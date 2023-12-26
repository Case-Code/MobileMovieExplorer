package com.casecode.mobilemovieexplorer.di.components;

import com.casecode.mobilemovieexplorer.di.modules.MovieModule;
import com.casecode.mobilemovieexplorer.presentation.view.MainActivity;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModel;

import dagger.Component;

@Component(modules = {MovieModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(MovieViewModel movieViewModel);
}
