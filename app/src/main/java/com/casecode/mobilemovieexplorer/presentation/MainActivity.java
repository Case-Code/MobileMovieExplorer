package com.casecode.mobilemovieexplorer.presentation;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.databinding.ActivityMainBinding;
import com.casecode.mobilemovieexplorer.presentation.utils.ViewExtensions;
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

    @Inject
    MovieViewModelFactory movieViewModelFactory;
    private ActivityMainBinding mBinding;
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
        setupNavigation();
        setupViewModel();
        fetchViewModel();
        observeViewModel();

    }

    private void setupNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        navController.addOnDestinationChangedListener((nav, navDestination, bundle) -> {
            mBinding.tvMainToolbar.setText(navDestination.getLabel());

            mBinding.imvMainToolbarLike.setVisibility(navDestination.getId() == R.id.nav_favorite_fragment ? View.INVISIBLE : View.VISIBLE);
            if (navDestination.getId() == R.id.nav_movies) {
                mBinding.imvMainIcon.setImageResource(R.drawable.ic_baseline_movie_filter_24);
            } else {
                mBinding.imvMainIcon.setImageResource(R.drawable.ic_back);
                mBinding.imvMainIcon.setOnClickListener(v -> {
                    navController.navigateUp();
                });
            }
        });

        setupClickedLiked(navController);
    }

    private void setupClickedLiked(NavController navController) {
        mBinding.imvMainToolbarLike.setOnClickListener(v -> {

            navController
                    .navigate(R.id.nav_favorite_fragment);
        });
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

    }

    /**
     * Observes changes in the ViewModel's LiveData and updates the UI components accordingly.
     */
    private void observeViewModel() {
        observerNetworkMonitor();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}