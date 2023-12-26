package com.casecode.mobilemovieexplorer.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.casecode.mobilemovieexplorer.databinding.ActivityMainBinding;
import com.casecode.mobilemovieexplorer.presentation.utils.ViewExtensions;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModel;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import lombok.experimental.ExtensionMethod;
import timber.log.Timber;

@ExtensionMethod(ViewExtensions.class)
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Inject
    MovieViewModelFactory movieViewModelFactory;
    private MovieViewModel movieViewModel;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        mBinding.setLifecycleOwner(this);

        setContentView(mBinding.getRoot());

        mBinding.textViewMain.setOnClickListener(view -> {
            view.showSnackbar("Snackbar Text", Snackbar.LENGTH_SHORT);
        });

        setupViewModel();

        fetchViewModel();
        observerViewModel();


    }

    private void setupViewModel() {
        movieViewModel = new ViewModelProvider(this, movieViewModelFactory).get(MovieViewModel.class);
    }

    private void fetchViewModel() {
        movieViewModel.setNetworkMonitor();
        movieViewModel.fetchMovies();
        movieViewModel.fetchDemoMovies();
        movieViewModel.fetchMovieDetails(640146);  // Replace with a valid movieId
        movieViewModel.fetchDemoDetails(297761);   // Replace with a valid demoId
    }

    private void observerViewModel() {
        observerNetworkMonitor();
        observerMovies();
        observerDemoMovies();
        observerMoviesDetails();
        observerDemoDetailsMovie();
    }

    private void observerDemoDetailsMovie() {
        movieViewModel.getDemoDetailsLiveData().observe(this, demoDetailsResponse -> {
            Timber.tag(TAG).d("Demo details response received: %s", demoDetailsResponse);
            switch (demoDetailsResponse.status) {
                case LOADING->{
                    Timber.tag(TAG).d("Demo details LOADING");

                }
                case SUCCESS -> {
                    Timber.tag(TAG).d("Demo details response received: %s", demoDetailsResponse);

                }
                case ERROR -> {
                    Timber.tag(TAG).e("Demo detailsERROR: %s", demoDetailsResponse.message);

                }
                case NULL -> {
                    Timber.tag(TAG).e("Demo details NULL");

                }
            }
        });
    }

    private void observerMoviesDetails() {
        movieViewModel.getMovieDetailsLiveData().observe(this, moviesDetailsResponse -> {
            switch (moviesDetailsResponse.status) {
                case LOADING->{
                    Timber.tag(TAG).d("Movie details LOADING");

                }
                case SUCCESS -> {
                    Timber.tag(TAG).d("Movie details response received: %s", moviesDetailsResponse);

                }
                case ERROR -> {
                    Timber.tag(TAG).e("Movie details ERROR: %s", moviesDetailsResponse.message);

                }
                case NULL -> {
                    Timber.tag(TAG).e("Movie details NULL");

                }
            }
        });
    }

    private void observerDemoMovies() {
        movieViewModel.getDemoMoviesLiveData().observe(this, demoResponse -> {
            switch (demoResponse.status) {
                case LOADING->{
                    Timber.tag(TAG).d("Demo movies LOADING");

                }
                case SUCCESS -> {
                    Timber.tag(TAG).d("Demo movies response received: %s", demoResponse);

                }
                case ERROR -> {
                    Timber.tag(TAG).e("Demo movies ERROR: %s", demoResponse.message);

                }
                case NULL -> {
                    Timber.tag(TAG).e("Demo movies NULL");

                }
            }
        });
    }

    private void observerMovies() {
        movieViewModel.getMoviesLiveData().observe(this, moviesResponse -> {
            switch (moviesResponse.status) {
                case LOADING->{
                    Timber.tag(TAG).d("Movies response LOADING");

                }
                case SUCCESS -> {
                    Timber.tag(TAG).d("Movies response received: %s", moviesResponse);

                }
                case ERROR -> {
                    Timber.tag(TAG).e("Movies response ERROR: %s", moviesResponse.message);

                }
                case NULL -> {
                    Timber.tag(TAG).e("Movies response NULL: %s", moviesResponse.message);

                }
            }
        });
    }

    private void observerNetworkMonitor() {
        movieViewModel.getIsOnline().observe(this, isOnline -> {
            if (Boolean.TRUE.equals(isOnline)) {
                Timber.e("isOnline = %s", true);
            } else {
                Timber.e("isOnline = %s", false);
            }
        });
    }


}