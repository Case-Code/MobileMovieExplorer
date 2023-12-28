package com.casecode.mobilemovieexplorer.presentation.adapter;


/**
 * Created by Mahmoud Abdalhafeez on 12/27/223
 */
public @FunctionalInterface interface ItemClickListener<T> {
        void onItemClick(T data);

}
