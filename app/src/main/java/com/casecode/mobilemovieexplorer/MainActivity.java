package com.casecode.mobilemovieexplorer;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Dagger injection
        MobileMovieExplorerApplication.getAppComponent().inject(this);

        // Now you can use movieViewModel
        movieViewModel.fetchMovies();

        // Observe moviesLiveData
        movieViewModel.getMoviesLiveData().observe(this, new Observer<MoviesResponse>() {
            @Override
            public void onChanged(MoviesResponse moviesResponse) {
                // Handle changes in moviesLiveData
                Log.d("MainActivity", "Movies response received: " + moviesResponse);
            }
        });

        // Observe demoMoviesLiveData
        movieViewModel.getDemoMoviesLiveData().observe(this, new Observer<DemoResponse>() {
            @Override
            public void onChanged(DemoResponse demoResponse) {
                // Handle changes in demoMoviesLiveData
                Log.d("MainActivity", "Demo movies response received: " + demoResponse);
            }
        });

        // Observe movieDetailsLiveData
        movieViewModel.getMovieDetailsLiveData().observe(this, new Observer<MoviesDetailsResponse>() {
            @Override
            public void onChanged(MoviesDetailsResponse moviesDetailsResponse) {
                // Handle changes in movieDetailsLiveData
                Log.d("MainActivity", "Movie details response received: " + moviesDetailsResponse);
            }
        });

        // Observe demoDetailsLiveData
        movieViewModel.getDemoDetailsLiveData().observe(this, new Observer<DemoDetailsResponse>() {
            @Override
            public void onChanged(DemoDetailsResponse demoDetailsResponse) {
                // Handle changes in demoDetailsLiveData
                Log.d("MainActivity", "Demo details response received: " + demoDetailsResponse);
            }
        });

        // Observe errorLiveData
        movieViewModel.getErrorLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                // Handle errors
                Log.e("MainActivity", "Error: " + errorMessage);
            }
        });

        // Trigger API calls
        movieViewModel.fetchMovies();
        movieViewModel.fetchDemoMovies();
        movieViewModel.fetchMovieDetails(640146);  // Replace with a valid movieId
        movieViewModel.fetchDemoDetails(297761);   // Replace with a valid demoId
    }
}