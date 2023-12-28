package com.casecode.mobilemovieexplorer.presentation.adapter;

import com.casecode.mobilemovieexplorer.domain.model.movies.Movie;

/**
 * Created by Mahmoud Abdalhafeez on 12/27/223
 */
public @FunctionalInterface interface ItemClickListener {
        void onItemClick(Movie movie);

}
