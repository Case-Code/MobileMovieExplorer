package com.casecode.mobilemovieexplorer.presentation.view;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.paging.LoadState;

import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.databinding.FragmentMoviesBinding;
import com.casecode.mobilemovieexplorer.domain.model.demo.DemoMovie;
import com.casecode.mobilemovieexplorer.domain.model.movies.Movie;
import com.casecode.mobilemovieexplorer.presentation.adapter.MoviesAdapter;
import com.casecode.mobilemovieexplorer.presentation.adapter.SliderDemoAdapter;
import com.casecode.mobilemovieexplorer.presentation.utils.ViewExtensions;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModel;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModelFactory;
import com.facebook.shimmer.Shimmer;
import com.google.android.material.snackbar.BaseTransientBottomBar;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import lombok.experimental.ExtensionMethod;
import timber.log.Timber;

@ExtensionMethod(ViewExtensions.class)
@AndroidEntryPoint
public class MoviesFragment extends Fragment {
    private static final String TAG = "MoviesFragment";
    @Inject
    MovieViewModelFactory movieViewModelFactory;
    private FragmentMoviesBinding mBinding;
    private MovieViewModel movieViewModel;
    private MoviesAdapter moviesAdapter;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentMoviesBinding.inflate(inflater, container, false);

        // Enable the options menu in the fragment
        setHasOptionsMenu(true);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        setupUi();
    }

    private void setupUi() {
        setupShimmerAnimation();
        setupViewModel();
        initializeAdapters();
        setupObserver();
        setupRefreshView();
        // Call the method to update the options menu
        updateOptionsMenu();
    }

    private void setupShimmerAnimation() {
        var shimmer = new Shimmer.AlphaHighlightBuilder().setDuration(
                        2000L) // how long the shimmering animation takes to do one full sweep
                .setRepeatMode(ValueAnimator.REVERSE)
                .setAutoStart(true)
                .build();

        mBinding.shMovies.setShimmer(shimmer);

    }

    private void startAnimation() {
        mBinding.shMovies.setVisibility(View.VISIBLE);
        mBinding.shMovies.startShimmer();
        mBinding.groupMoviesData.setVisibility(View.GONE);
        mBinding.groupMoviesError.setVisibility(View.GONE);
    }

    private void stopAnimationAndShowImageError() {
        mBinding.shMovies.stopShimmer();
        mBinding.groupMoviesError.setVisibility(View.VISIBLE);
        mBinding.shMovies.setVisibility(View.GONE);

        mBinding.groupMoviesData.setVisibility(View.GONE);
    }

    private void stopAnimation() {
        mBinding.shMovies.stopShimmer();
        mBinding.shMovies.setVisibility(View.GONE);
        mBinding.groupMoviesData.setVisibility(View.VISIBLE);
        mBinding.groupMoviesError.setVisibility(View.GONE);
    }

    private void setupViewModel() {
        movieViewModel = new ViewModelProvider(requireActivity(), movieViewModelFactory)
                .get(MovieViewModel.class);
    }

    private void initializeAdapters() {
        initDemoAdapter();
        initMoviesAdapter();
    }

    private void initDemoAdapter() {
        var slideDemoAdapter = new SliderDemoAdapter();
        slideDemoAdapter.setItemClickListener(this::onItemDemoMovieClick);
        mBinding.setDemoAdapter(slideDemoAdapter);

    }

    private void onItemDemoMovieClick(View view, DemoMovie demoMovie) {
        movieViewModel.setDemoMovieIdSelected(demoMovie.id());

        Navigation.findNavController(view)
                .navigate(R.id.action_nav_movies_fragment_to_nav_details_fragment);
    }

    private void initMoviesAdapter() {
        moviesAdapter = new MoviesAdapter(this::onItemMovieClick);

        initializeLoadStateListenerMoviesAdapter();
        mBinding.setMoviesAdapter(moviesAdapter);
        observerMoviesPaging();
    }

    private void initializeLoadStateListenerMoviesAdapter() {
        moviesAdapter.addLoadStateListener(loadState -> {
            if (loadState.getSource().getRefresh() instanceof LoadState.NotLoading notLoading) {
                if (notLoading.getEndOfPaginationReached() && moviesAdapter.getItemCount() < 1) {
                    Timber.tag(TAG).i("addLoadStateListener: empty list");
                    stopAnimationAndShowImageError();
                } else {
                    Timber.tag(TAG).i("addLoadStateListener: list present");

                    new Handler(Looper.getMainLooper())
                            .postDelayed(this::stopAnimation, 800L);

                }
            } else if (loadState.getSource().getRefresh() instanceof LoadState.Loading) {
                startAnimation();
                Timber.tag(TAG).d("addLoadStateListener  LOADING");

            } else if (loadState.getSource().getRefresh() instanceof LoadState.Error error) {
                stopAnimationAndShowImageError();
                Timber.tag(TAG).d("addLoadStateListener  Error" + error);

                mBinding.getRoot().showSnackbar(getString(R.string.movies_loading_error), BaseTransientBottomBar.LENGTH_SHORT);
            }
            return null;
        });
    }

    private void observerMoviesPaging() {

        movieViewModel.getMoviesPaging().observe(getViewLifecycleOwner(), pagingData -> {
            Timber.e("observerMoviesPaging, pagingData = %s", pagingData);
            moviesAdapter.submitData(getViewLifecycleOwner().getLifecycle(), pagingData);
        });


    }

    private void onItemMovieClick(View view, Movie movie) {
        movieViewModel.setMovieIdSelected(movie.id());

        Navigation.findNavController(view).navigate(R.id.action_nav_movies_fragment_to_nav_details_fragment);
    }

    private void setupObserver() {
        setupNetworkMonitor();
        setupObserverDemoMovies();
    }

    private void setupNetworkMonitor() {
        movieViewModel.getIsOnline().observe(getViewLifecycleOwner(), isOnline -> {
            if (Boolean.TRUE.equals(isOnline)) {
                movieViewModel.fetchMoviesPaging(getViewLifecycleOwner());
                movieViewModel.fetchDemoMovies();

            } else {
                Timber.e("isOnline = %s", false);
                mBinding.getRoot().showSnackbar(getString(R.string.all_network_error), BaseTransientBottomBar.LENGTH_SHORT);
            }
        });
    }


    private void setupObserverDemoMovies() {
        movieViewModel.getDemoMoviesLiveData().observe(getViewLifecycleOwner(), demoResponse -> {
            switch (demoResponse.status) {
                case LOADING -> {
                    Timber.tag(TAG).d("Demo movies LOADING");
                }

                case SUCCESS -> {
                    Timber.tag(TAG).d("Demo movies : %s", demoResponse);
                    mBinding.setDemoList(demoResponse.getData().results());

                }
                case ERROR -> {
                    Timber.tag(TAG).e("Demo movies ERROR: %s", demoResponse.message);
                }
                case NULL -> {
                    Timber.tag(TAG).e("Demo movies NULL");
                    stopAnimation();
                }
            }
        });
    }

    private void setupRefreshView() {
        mBinding.swipeMovies.setOnRefreshListener(() -> {
            setupNetworkMonitor();
            mBinding.swipeMovies.setRefreshing(false);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        //  startAnimation();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopAnimation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }

    private void updateOptionsMenu() {
        // Invalidate the options menu to trigger onPrepareOptionsMenu
        requireActivity().invalidateOptionsMenu();
    }
}