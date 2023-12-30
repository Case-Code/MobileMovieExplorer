package com.casecode.mobilemovieexplorer.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.data.mapper.FavoriteMovies;
import com.casecode.mobilemovieexplorer.databinding.FragmentMoviesDetailsBinding;
import com.casecode.mobilemovieexplorer.presentation.adapter.CastAdapter;
import com.casecode.mobilemovieexplorer.presentation.adapter.GenresAdapter;
import com.casecode.mobilemovieexplorer.presentation.utils.EventObserver;
import com.casecode.mobilemovieexplorer.presentation.utils.ViewExtensions;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.FavoriteViewModel;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.FavoriteViewModelFactory;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModel;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModelFactory;
import com.google.android.material.snackbar.BaseTransientBottomBar;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import lombok.experimental.ExtensionMethod;
import timber.log.Timber;


@ExtensionMethod(ViewExtensions.class)
@AndroidEntryPoint
public class MoviesDetailsFragment extends Fragment {

    @Inject
    FavoriteViewModelFactory favoriteViewModelFactory;
    @Inject
    MovieViewModelFactory movieViewModelFactory;
    private FragmentMoviesDetailsBinding binding;
    private FavoriteViewModel favoriteViewModel;
    private MovieViewModel movieViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        setupUi();

    }

    private void setupUi() {
        setupViewModel();
        setupAdapter();

        observerMovieIdSelected();
        observerViewModel();

        setupClickedListener();
    }
    private void setupViewModel() {
        movieViewModel = new ViewModelProvider(requireActivity(), movieViewModelFactory)
                .get(MovieViewModel.class);
        favoriteViewModel = new ViewModelProvider(this, favoriteViewModelFactory)
                .get(FavoriteViewModel.class);
    }

    private void setupAdapter() {
        binding.setCastAdapter(new CastAdapter());
        binding.setGenerAdapter(new GenresAdapter());

    }
    private void setupClickedListener() {

        // Set an OnClickListener for the likeButton
        binding.imvMoviesDetailsLike.setOnClickListener(viewLikeButton -> {
            // Toggle the selected state
            boolean isLiked = viewLikeButton.isSelected();
            viewLikeButton.setSelected(!isLiked);
            // Change the drawable based on the selected state
            updateButtonDrawable(viewLikeButton);
        });
        binding.setShareListener((view, data) -> {
            Timber.e("shareLink = %s", data);
            String moviePage = data.toString();
            shareLink(moviePage);

        });

    }

    private void updateButtonDrawable(View likeButton) {
        //TODO: handle logic for add and remove movie favorite in db.
        int drawableResId;
        var moviesDetails = movieViewModel.getMovieDetailsLiveData().getValue().getData();
        if (likeButton.isSelected()) {
            drawableResId = R.drawable.favorite_crusta_24;
            favoriteViewModel.addFavoriteMovie(FavoriteMovies.asMoviesDetailsMovie(moviesDetails));

        } else {
            drawableResId = R.drawable.favorite_white_24;
            favoriteViewModel.deleteFavoriteMovie(FavoriteMovies.asMoviesDetailsMovie(moviesDetails));

        }
        binding.imvMoviesDetailsLike.setImageResource(drawableResId);
    }
    private void shareLink(String link) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, link);

        startActivity(Intent.createChooser(shareIntent, "Share link using"));
    }
    private void observerViewModel() {
        movieViewModel.getDemoDetailsLiveData().observe(getViewLifecycleOwner(), deomResource -> {
            switch (deomResource.getStatus()) {
                case LOADING -> {

                }
                case SUCCESS -> {
                    binding.setIsDemo(true);
                    binding.setDemoMovie(deomResource.getData());
                    binding.executePendingBindings();

                }
                case ERROR -> {

                }
            }
        });
        movieViewModel.getMovieDetailsLiveData().observe(getViewLifecycleOwner(), resource -> {
            switch (resource.getStatus()) {
                case LOADING -> {
                }
                case SUCCESS -> {
                    binding.setIsDemo(false);
                    // Handle success state, and access movie details from resource.getData()
                    binding.setMovie(resource.getData());
                    // Do something with movieDetails
                    binding.executePendingBindings();

                }
                case ERROR -> {
                    // Handle error state

                }
            }
        });
    }


    private void observerMovieIdSelected() {
        movieViewModel.getUserMessage().observe(getViewLifecycleOwner(), idMessage -> {
            if (idMessage == null) {

                movieViewModel.getMovieIdSelected().observe(getViewLifecycleOwner(),
                        new EventObserver<>(id -> movieViewModel.fetchMovieDetails(id)));

                movieViewModel.getFavoriteMovieIdSelected().observe(getViewLifecycleOwner(),
                        new EventObserver<>(id -> movieViewModel.fetchMovieDetails(id)));

                movieViewModel.getDemoMovieIdSelected().observe(getViewLifecycleOwner(),
                        new EventObserver<>(id -> movieViewModel.fetchDemoDetails(id)));

            } else {
                binding.getRoot().showSnackbar(getString(idMessage), BaseTransientBottomBar.LENGTH_LONG);
                movieViewModel.snackbarMessageShown();
                Timber.e("isOnline false");
            }

        });
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Release the binding when the view is destroyed

        // Remove the observer when the fragment view is destroyed
//        movieViewModel.getMovieDetailsLiveData().removeObservers(this);
    }

}
