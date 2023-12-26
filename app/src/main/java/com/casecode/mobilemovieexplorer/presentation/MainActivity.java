package com.casecode.mobilemovieexplorer.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModel;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModelFactory;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Inject
    MovieViewModelFactory movieViewModelFactory;

    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViewModel();
        // Now you can use movieViewModel
        movieViewModel.fetchMovies();

        // Observe moviesLiveData
        movieViewModel.getMoviesLiveData().observe(this, new Observer<MoviesResponse>() {
            @Override
            public void onChanged(MoviesResponse moviesResponse) {
                // Handle changes in moviesLiveData
                Timber.tag(TAG).d("Movies response received: " + moviesResponse);
            }
        });

        // Observe demoMoviesLiveData
        movieViewModel.getDemoMoviesLiveData().observe(this, new Observer<DemoResponse>() {
            @Override
            public void onChanged(DemoResponse demoResponse) {
                // Handle changes in demoMoviesLiveData
                Timber.tag(TAG).d("Demo movies response received: " + demoResponse);
            }
        });

        // Observe movieDetailsLiveData
        movieViewModel.getMovieDetailsLiveData().observe(this, new Observer<MoviesDetailsResponse>() {
            @Override
            public void onChanged(MoviesDetailsResponse moviesDetailsResponse) {
                // Handle changes in movieDetailsLiveData
                Timber.tag(TAG).d("Movie details response received: " + moviesDetailsResponse);
            }
        });

        // Observe demoDetailsLiveData
        movieViewModel.getDemoDetailsLiveData().observe(this, new Observer<DemoDetailsResponse>() {
            @Override
            public void onChanged(DemoDetailsResponse demoDetailsResponse) {
                // Handle changes in demoDetailsLiveData
                Timber.tag(TAG).d("Demo details response received: " + demoDetailsResponse);
            }
        });

        // Observe errorLiveData
        movieViewModel.getErrorLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                // Handle errors
                Timber.tag("MainActivity").e("Error: " + errorMessage);
            }
        });

        // Trigger API calls
        movieViewModel.fetchMovies();
        movieViewModel.fetchDemoMovies();
        movieViewModel.fetchMovieDetails(640146);  // Replace with a valid movieId
        movieViewModel.fetchDemoDetails(297761);   // Replace with a valid demoId
    }

    private void setupViewModel() {
        movieViewModel = new ViewModelProvider(this, movieViewModelFactory).get(MovieViewModel.class);
    }
}