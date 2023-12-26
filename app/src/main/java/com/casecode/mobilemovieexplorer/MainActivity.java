package com.casecode.mobilemovieexplorer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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


        // Initialize ViewModel
        movieViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);

        // Example: Observe MoviesResponse
        movieViewModel.getMovies().observe(this, new Observer<MoviesResponse>() {
            @Override
            public void onChanged(MoviesResponse moviesResponse) {
                // Update UI with moviesResponse data
                // For example, display a list of movies in a RecyclerView
            }
        });

        // Example: Observe DemoResponse
        movieViewModel.getDemoMovies().observe(this, new Observer<DemoResponse>() {
            @Override
            public void onChanged(DemoResponse demoResponse) {
                // Update UI with demoResponse data
            }
        });

        // Example: Observe MovieDetailsResponse
        int movieId = 123; // Replace with the actual movie ID
        movieViewModel.getMovieDetails(movieId).observe(this, new Observer<MoviesDetailsResponse>() {
            @Override
            public void onChanged(MoviesDetailsResponse moviesDetailsResponse) {
                // Update UI with moviesDetailsResponse data
            }
        });

        // Example: Observe DemoDetailsResponse
        int demoId = 456; // Replace with the actual demo ID
        movieViewModel.getDemoDetails(demoId).observe(this, new Observer<DemoDetailsResponse>() {
            @Override
            public void onChanged(DemoDetailsResponse demoDetailsResponse) {
                // Update UI with demoDetailsResponse data
            }
        });
    }
}