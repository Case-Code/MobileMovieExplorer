package com.casecode.mobilemovieexplorer.presentation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

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

    private AppBarConfiguration mAppBarConfiguration;
    private NavController mNavController;

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
        initToolbar();
        setupViewModel();
        fetchViewModel();
        observeViewModel();
    }

    private void initToolbar() {
        // Find the NavHostFragment
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        // Get the NavController from the NavHostFragment
        assert navHostFragment != null;
        mNavController = navHostFragment.getNavController();

        // Create an AppBarConfiguration with the top-level destinations
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_movies,
                R.id.nav_favorite_fragment,
                R.id.nav_details_fragment
        ).build();

        setSupportActionBar(mBinding.toolbar);

        // Associate the Toolbar with NavController using NavigationUI
        NavigationUI.setupWithNavController(mBinding.toolbar, mNavController, mAppBarConfiguration);

        // Listen for destination changes to update the icon
        mNavController.addOnDestinationChangedListener((controller, destination, arguments) ->
                updateToolbarIcon(destination.getId()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(mNavController, mAppBarConfiguration) || super.onSupportNavigateUp();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.item_favorite_movies) {
            // Handle the favorite movies item click
            // Example: show a Toast
            mNavController.navigate(R.id.nav_favorite_fragment);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Hide item_favorite_movies when in nav_favorite_fragment
        MenuItem favoriteMenuItem = menu.findItem(R.id.item_favorite_movies);
        favoriteMenuItem.setVisible(isFavoriteFragmentVisible());
        return super.onPrepareOptionsMenu(menu);
    }

    private boolean isFavoriteFragmentVisible() {
        return mNavController.getCurrentDestination() != null &&
                mNavController.getCurrentDestination().getId() != R.id.nav_favorite_fragment;
    }

    private void updateToolbarIcon(int destinationId) {
        // Set the common icon by default
        int iconResId = R.drawable.movie_white_24;

        // Customize the icon for specific fragments
        if (destinationId == R.id.nav_favorite_fragment) {
            iconResId = R.drawable.ic_back;
        } else if (destinationId == R.id.nav_details_fragment) {
            iconResId = R.drawable.ic_back;
        }

        // Set the icon in the Toolbar
        mBinding.toolbar.setNavigationIcon(iconResId);
    }
}