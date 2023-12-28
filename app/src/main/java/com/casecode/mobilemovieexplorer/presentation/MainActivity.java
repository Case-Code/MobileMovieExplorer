package com.casecode.mobilemovieexplorer.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;

import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.databinding.ActivityMainBinding;
import com.casecode.mobilemovieexplorer.presentation.utils.ViewExtensions;
import com.casecode.mobilemovieexplorer.presentation.view.MoviesDetailsFragment;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModel;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModelFactory;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import lombok.experimental.ExtensionMethod;
import timber.log.Timber;

/**
 * Main activity class representing the entry point of the application.
 * Uses Hilt for dependency injection and integrates with the MovieViewModel to manage movie-related data.
 * Observes LiveData to update the UI components based on changes in the data.
 */
@ExtensionMethod(ViewExtensions.class)
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding mBinding;
    private NavController navController;
    @Inject
    MovieViewModelFactory movieViewModelFactory;
    private MovieViewModel movieViewModel;

    /**
     * Called when the activity is first created. Initializes UI components, sets up ViewModel,
     * and fetches movie-related data.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           Note: Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        mBinding.setLifecycleOwner(this);
        // Create an instance of MoviesDetailsFragment


        setContentView(mBinding.getRoot());
        setupTitle();
        setupViewModel();
        fetchViewModel();
        observeViewModel();
    }

    private void setupTitle(){
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        navController.addOnDestinationChangedListener((navController, navDestination, bundle) ->
                setTitle(navDestination.getLabel()));
    }
    /**
     * Initializes the MovieViewModel using Hilt for dependency injection.
     */
    private void setupViewModel() {
        movieViewModel = new ViewModelProvider(this, movieViewModelFactory)
                .get(MovieViewModel.class);
    }

    /**
     * Fetches data from the ViewModel, including movies, demo movies, movie details, and demo details.
     */
    private void fetchViewModel() {
        movieViewModel.setNetworkMonitor();
      /*  movieViewModel.fetchMovies();
        movieViewModel.fetchDemoMovies();
        movieViewModel.fetchMovieDetails(640146);  // Replace with a valid movieId
        movieViewModel.fetchDemoDetails(297761);   // Replace with a valid demoId*/
    }

    /**
     * Observes changes in the ViewModel's LiveData and updates the UI components accordingly.
     */
    private void observeViewModel() {
        observerNetworkMonitor();
        observerMovies();
        observerDemoMovies();
        observerMoviesDetails();
        observerDemoDetailsMovie();
    }

    /**
     * Observes changes in demo details LiveData and logs corresponding events.
     */
    private void observerDemoDetailsMovie() {
        movieViewModel.getDemoDetailsLiveData().observe(this, demoDetailsResponse -> {
            Timber.tag(TAG).d("Demo details response received: %s", demoDetailsResponse);
            switch (demoDetailsResponse.status) {
                case LOADING -> Timber.tag(TAG).d("Demo details LOADING");
                case SUCCESS -> Timber.tag(TAG).d("Demo details response received: %s",
                        demoDetailsResponse);
                case ERROR ->
                        Timber.tag(TAG).e("Demo details ERROR: %s", demoDetailsResponse.message);
                case NULL -> Timber.tag(TAG).e("Demo details NULL");
            }
        });
    }

    /**
     * Observes changes in movie details LiveData and logs corresponding events.
     */
    private void observerMoviesDetails() {
        movieViewModel.getMovieDetailsLiveData().observe(this, moviesDetailsResponse -> {
            switch (moviesDetailsResponse.status) {
                case LOADING -> Timber.tag(TAG).d("Movie details LOADING");
                case SUCCESS -> Timber.tag(TAG).d("Movie details response received: %s",
                        moviesDetailsResponse);
                case ERROR ->
                        Timber.tag(TAG).e("Movie details ERROR: %s", moviesDetailsResponse.message);
                case NULL -> Timber.tag(TAG).e("Movie details NULL");
            }
        });
    }

    /**
     * Observes changes in demo movies LiveData and logs corresponding events.
     */
    private void observerDemoMovies() {
        movieViewModel.getDemoMoviesLiveData().observe(this, demoResponse -> {
            switch (demoResponse.status) {
                case LOADING -> Timber.tag(TAG).d("Demo movies LOADING");
                case SUCCESS ->
                        Timber.tag(TAG).d("Demo movies response received: %s", demoResponse);
                case ERROR -> Timber.tag(TAG).e("Demo movies ERROR: %s", demoResponse.message);
                case NULL -> Timber.tag(TAG).e("Demo movies NULL");
            }
        });
    }

    /**
     * Observes changes in movies LiveData and logs corresponding events.
     */
    private void observerMovies() {
        movieViewModel.getMoviesLiveData().observe(this, moviesResponse -> {
            switch (moviesResponse.status) {
                case LOADING -> Timber.tag(TAG).d("Movies response LOADING");
                case SUCCESS -> Timber.tag(TAG).d("Movies response received: %s", moviesResponse);
                case ERROR ->
                        Timber.tag(TAG).e("Movies response ERROR: %s", moviesResponse.message);
                case NULL -> Timber.tag(TAG).e("Movies response NULL: %s", moviesResponse.message);
            }
        });
    }

    /**
     * Observes changes in the network monitor LiveData and logs the connectivity status.
     */
    private void observerNetworkMonitor() {
        movieViewModel.getIsOnline().observe(this, isOnline -> {
            mBinding.setIsAvailable(isOnline);

            if (Boolean.TRUE.equals(isOnline)) {
                Timber.e("isOnline = %s", true);
            } else {
                Timber.e("isOnline = %s", false);
            }
        });
    }
}