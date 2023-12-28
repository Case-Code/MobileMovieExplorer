package com.casecode.mobilemovieexplorer.presentation.view;

import static com.casecode.mobilemovieexplorer.presentation.utils.Status.ERROR;
import static com.casecode.mobilemovieexplorer.presentation.utils.Status.LOADING;
import static com.casecode.mobilemovieexplorer.presentation.utils.Status.SUCCESS;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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
        movieViewModel = new ViewModelProvider(requireActivity(), movieViewModelFactory)
                .get(MovieViewModel.class);
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

                    // Assuming 'binding' is the ViewBinding instance for your layout
                    // Example of setting an image using Glide with a ProgressBar
                    Glide.with(this)
                            .load(movieDetails.getBackdropPath())
                            .placeholder(R.drawable.panorama_24) // Placeholder image while loading
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    // Hide progress bar on failure
                                    binding.progressBar.setVisibility(View.GONE);
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    // Hide progress bar on success
                                    binding.progressBar.setVisibility(View.GONE);
                                    return false;
                                }
                            })
                            .into(binding.imageBackdropPath);

                    // Show progress bar while loading
                    binding.progressBar.setVisibility(View.VISIBLE);


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

        binding.imageButtonBack.setOnClickListener(v -> {
            // Get the NavController
            NavController navController = Navigation.findNavController(v);

            // Navigate back
            navController.navigateUp();
        });

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
        movieViewModel.fetchMovieDetails();
    }

    private void setupGenresRecyclerView(List<Genre> genres) {
        // Set up RecyclerView for genres using View Binding
        binding.recyclerGenres.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        GenresAdapter genresAdapter = new GenresAdapter(genres);
        binding.recyclerGenres.setAdapter(genresAdapter);
    }

    private void updateButtonDrawable() {
        int drawableResId = likeButton.isSelected() ? R.drawable.favorite_crusta_24 : R.drawable.favorite_white_24;
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
