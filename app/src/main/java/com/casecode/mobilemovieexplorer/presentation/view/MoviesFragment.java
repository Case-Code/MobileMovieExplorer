package com.casecode.mobilemovieexplorer.presentation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.casecode.mobilemovieexplorer.databinding.FragmentMoviesBinding;


public class MoviesFragment extends Fragment {

    private FragmentMoviesBinding mMoviesBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMoviesBinding = FragmentMoviesBinding.inflate(inflater, container, false);
        return mMoviesBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMoviesBinding.setLifecycleOwner(getViewLifecycleOwner());
        // TODO: Add logic here.
    }
}