package com.casecode.mobilemovieexplorer.presentation.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.casecode.mobilemovieexplorer.MyApplication;
import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inject dependencies
        ((MyApplication) getApplication()).getAppComponent().inject(this);

        // Observe movies data
        movieViewModel.getMovies().observe(this, moviesResponse -> {
            if (moviesResponse != null) {
                Log.d("MovieViewModel", "Movies response received: " + moviesResponse.toString());

                // Update UI with moviesResponse
            } else {
                Log.e("MovieViewModel", "Movies response is null");
            }
        });

        // Observe demo movies data
        movieViewModel.getDemoMovies().observe(this, demoResponse -> {
            if (demoResponse != null) {
                Log.d("MovieViewModel", "Demo movies response received: " + demoResponse.toString());

                // Update UI with demoResponse
            } else {
                Log.e("MovieViewModel", "Demo movies response is null");
            }
        });

        // Observe movie details data
        int movieId = 1; // replace with the actual movieId
        movieViewModel.getMovieDetails(movieId).observe(this, movieDetailsResponse -> {
            if (movieDetailsResponse != null) {
                Log.d("MovieViewModel", "Movie details response received: " + movieDetailsResponse.toString());

                // Update UI with movieDetailsResponse
            } else {
                Log.e("MovieViewModel", "Movie details response is null");
            }
        });

        // Observe demo details data
        int demoId = 1; // replace with the actual demoId
        movieViewModel.getDemoDetails(demoId).observe(this, demoDetailsResponse -> {
            if (demoDetailsResponse != null) {
                Log.d("MovieViewModel", "Demo details response received: " + demoDetailsResponse.toString());

                // Update UI with demoDetailsResponse
            } else {
                Log.e("MovieViewModel", "Demo details response is null");
            }
        });

    }
}
