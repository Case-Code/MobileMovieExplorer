package com.casecode.mobilemovieexplorer.presentation.view;

import static com.casecode.mobilemovieexplorer.presentation.utils.Status.ERROR;
import static com.casecode.mobilemovieexplorer.presentation.utils.Status.LOADING;
import static com.casecode.mobilemovieexplorer.presentation.utils.Status.SUCCESS;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.databinding.FragmentMoviesDetailsBinding;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.Cast;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.Genre;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;
import com.casecode.mobilemovieexplorer.presentation.adapter.CastAdapter;
import com.casecode.mobilemovieexplorer.presentation.adapter.GenresAdapter;
import com.casecode.mobilemovieexplorer.presentation.utils.ViewExtensions;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModel;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import lombok.experimental.ExtensionMethod;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoviesDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@ExtensionMethod(ViewExtensions.class)
@AndroidEntryPoint
public class MoviesDetailsFragment extends Fragment {

    private FragmentMoviesDetailsBinding binding;
    private ImageButton likeButton;

    private List<Cast> castList;

    @Inject
    MovieViewModelFactory movieViewModelFactory;

    private MovieViewModel movieViewModel;

    public MoviesDetailsFragment() {
        // Required empty public constructor
    }

    public static MoviesDetailsFragment newInstance(String param1, String param2) {
        MoviesDetailsFragment fragment = new MoviesDetailsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize ViewModel
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        // Observe LiveData for movie details
        movieViewModel.getMovieDetailsLiveData().observe(this, resource -> {
            switch (resource.getStatus()) {
                case LOADING:
                    // Handle loading state
                    break;
                case SUCCESS:
                    // Handle success state, and access movie details from resource.getData()
                    MoviesDetailsResponse movieDetails = resource.getData();
                    // Do something with movieDetails

                    // Example of setting an image using Glide and View Binding
                    Glide.with(this)
                            .load(movieDetails.getBackdropPath())
                            .into(binding.imageBackdropPath);

                    // Example of setting text to a TextView using View Binding
                    binding.textOverview.setText(movieDetails.getOverview());

                    // Example of setting text to a TextView using View Binding
                    binding.textVoteAverage.setText(String.valueOf(movieDetails.getVoteAverage()));

                    // Example of setting text to a TextView with formatted string using View Binding
                    String runtimeFormat = getString(R.string.runtime_format);
                    String runtime = String.valueOf(movieDetails.getRuntime());
                    binding.textRuntime.setText(String.format(runtimeFormat, runtime));

                    // Set up RecyclerView for cast
                    LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
                    binding.recyclerViewCast.setLayoutManager(layoutManager);

                    // Assuming you have a method to get the cast list from movieDetails
                    List<Cast> castList = movieDetails.getCast();

                    // Create a CastAdapter and set it to the RecyclerView
                    CastAdapter castAdapter = new CastAdapter(castList);
                    binding.recyclerViewCast.setAdapter(castAdapter);

                    // Assuming you have a method to get the genre list from movieDetails
                    List<Genre> genres = movieDetails.getGenres();

                    setupGenresRecyclerView(genres);

                    break;
                case ERROR:
                    // Handle error state
                    String errorMessage = resource.getMessage();
                    // Display or log the error message
                    break;
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Bind views using View Binding
        likeButton = binding.imageButtonLike;

        // Set an OnClickListener for the likeButton
        likeButton.setOnClickListener(viewLikeButton -> {
            // Toggle the selected state
            boolean isLiked = likeButton.isSelected();
            likeButton.setSelected(!isLiked);

            // Change the drawable based on the selected state
            updateButtonDrawable();
        });

        // Fetch movie details when the fragment is created or some event occurs
        int movieId = 640146; // Replace with the actual movie ID
        movieViewModel.fetchMovieDetails(movieId);
    }

    private void setupGenresRecyclerView(List<Genre> genres) {
        // Set up RecyclerView for genres using View Binding
        binding.recyclerGenres.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        GenresAdapter genresAdapter = new GenresAdapter(genres);
        binding.recyclerGenres.setAdapter(genresAdapter);
    }

    private void updateButtonDrawable() {
        int drawableResId = likeButton.isSelected() ? R.drawable.favorite_fill_24 : R.drawable.favorite_24;
        likeButton.setImageResource(drawableResId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Release the binding when the view is destroyed

        // Remove the observer when the fragment view is destroyed
//        movieViewModel.getMovieDetailsLiveData().removeObservers(this);
    }

}