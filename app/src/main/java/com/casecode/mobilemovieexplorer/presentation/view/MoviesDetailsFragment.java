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

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import lombok.experimental.ExtensionMethod;
import timber.log.Timber;


@ExtensionMethod(ViewExtensions.class)
@AndroidEntryPoint
public class MoviesDetailsFragment extends Fragment {

    FavoriteViewModelFactory favoriteViewModelFactory;
    MovieViewModelFactory movieViewModelFactory;
    private FragmentMoviesDetailsBinding binding;
    private FavoriteViewModel favoriteViewModel;
    private MovieViewModel movieViewModel;



    @Inject
    public MoviesDetailsFragment(MovieViewModelFactory movieViewModelFactory, FavoriteViewModelFactory favoriteViewModelFactory)
    {
        this.movieViewModelFactory = movieViewModelFactory;
        this.favoriteViewModelFactory = favoriteViewModelFactory;
    }
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
        movieViewModel.getIsOnline().observe(getViewLifecycleOwner(), isOnline -> {
            if (Boolean.TRUE.equals(isOnline)) {
                observerMovieIdSelected();

            } else {
                Timber.e("isOnline = %s", false);
                showUiError();
                binding.getRoot().showSnackbar(getString(R.string.all_network_error), BaseTransientBottomBar.LENGTH_SHORT);
            }
        });
        observerViewModel();
        setupClickedListener();
        // Call the method to update the options menu
        updateOptionsMenu();
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
        int drawableResId;
        if (likeButton.isSelected()) {
            if (Boolean.TRUE.equals(binding.getIsDemo())) {
                var movieDemo = Objects.requireNonNull(movieViewModel.getDemoDetailsLiveData().getValue()).getData();
                favoriteViewModel.addFavoriteMovie(FavoriteMovies.asDomainDemoDetails(movieDemo));

            } else {

                var moviesDetails = Objects.requireNonNull(movieViewModel.getMovieDetailsLiveData().getValue()).getData();
                favoriteViewModel.addFavoriteMovie(FavoriteMovies.asMoviesDetailsMovie(moviesDetails));
            }
            drawableResId = R.drawable.favorite_crusta_24;

        } else {
            if (Boolean.TRUE.equals(binding.getIsDemo())) {
                var movieDemo = Objects.requireNonNull(movieViewModel.getDemoDetailsLiveData().getValue()).getData();

                favoriteViewModel.deleteFavoriteMovie(FavoriteMovies.asDomainDemoDetails(movieDemo));
            } else {
                var moviesDetails = Objects.requireNonNull(movieViewModel.getMovieDetailsLiveData().getValue()).getData();

                favoriteViewModel.deleteFavoriteMovie(FavoriteMovies.asMoviesDetailsMovie(moviesDetails));

            }
            drawableResId = R.drawable.favorite_24;

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
                case LOADING -> showUiLoading();
                case SUCCESS -> {
                    binding.setIsDemo(true);
                    favoriteViewModel.isFavoriteMovies(deomResource.getData().id());
                    binding.setDemoMovie(deomResource.getData());

                    showUiData();
                    binding.executePendingBindings();
                }
                case ERROR -> showUiError();
                case NULL -> showUiError();
            }
        });

        movieViewModel.getMovieDetailsLiveData().observe(getViewLifecycleOwner(), resource -> {
            switch (resource.getStatus()) {
                case LOADING -> showUiLoading();
                case SUCCESS -> {
                    showUiData();
                    binding.setIsDemo(false);

                    favoriteViewModel.isFavoriteMovies(resource.getData().id());
                    binding.setMovie(resource.getData());
                    // Do something with movieDetails
                    binding.executePendingBindings();

                }
                default -> // Handle error state
                        showUiError();
            }
        });

        favoriteViewModel.getIsFavoriteMovie().observe(getViewLifecycleOwner(), isFavorite -> {
            int drawableResId;
            if (Boolean.TRUE.equals(isFavorite)) {
                drawableResId = R.drawable.favorite_crusta_24;
                binding.imvMoviesDetailsLike.setSelected(true);

            } else {
                drawableResId = R.drawable.favorite_24;

            }
            binding.imvMoviesDetailsLike.setImageResource(drawableResId);

        });


    }

    private void showUiError() {
        binding.pgrDetails.setVisibility(View.GONE);
        binding.groupDetailsData.setVisibility(View.INVISIBLE);

        binding.groupDetailsError.setVisibility(View.VISIBLE);
    }

    private void showUiData() {
        binding.groupDetailsError.setVisibility(View.GONE);
        binding.pgrDetails.setVisibility(View.GONE);
        binding.groupDetailsData.setVisibility(View.VISIBLE);
    }

    private void showUiLoading() {
        binding.pgrDetails.setVisibility(View.VISIBLE);
        binding.groupDetailsData.setVisibility(View.INVISIBLE);
        binding.groupDetailsError.setVisibility(View.INVISIBLE);
    }


    private void observerMovieIdSelected() {
        movieViewModel.getUserMessage().observe(getViewLifecycleOwner(), idMessage -> {
            if (idMessage == null) {

                movieViewModel.getMovieIdSelected().observe(getViewLifecycleOwner(),
                        new EventObserver<>(id -> movieViewModel.fetchMovieDetails(id)));

                movieViewModel.getDemoMovieIdSelected().observe(getViewLifecycleOwner(),
                        new EventObserver<>(id -> movieViewModel.fetchDemoDetails(id)));
            } else {
                movieViewModel.snackbarMessageShown();
                Timber.e("isOnline false");
            }
        });
    }

    private void updateOptionsMenu() {
        requireActivity().invalidateOptionsMenu();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
