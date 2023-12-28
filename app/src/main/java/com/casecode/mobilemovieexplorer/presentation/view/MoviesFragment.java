package com.casecode.mobilemovieexplorer.presentation.view;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.databinding.FragmentMoviesBinding;
import com.casecode.mobilemovieexplorer.domain.model.demo.DemoMovie;
import com.casecode.mobilemovieexplorer.domain.model.movies.Movie;
import com.casecode.mobilemovieexplorer.presentation.adapter.DemoMoviesAdapter;
import com.casecode.mobilemovieexplorer.presentation.adapter.MoviesAdapter;
import com.casecode.mobilemovieexplorer.presentation.utils.ViewExtensions;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModel;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModelFactory;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

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
    private ShimmerFrameLayout mShimmerFrameLayout;
    private FragmentMoviesBinding mBinding;
    private MovieViewModel movieViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentMoviesBinding.inflate(inflater, container, false);
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
        setupObserver();
        setupAdapter();
        setupRefreshView();
    }

    private void setupShimmerAnimation() {
        var shimmer = new Shimmer.AlphaHighlightBuilder().setDuration(
                        2000L) // how long the shimmering animation takes to do one full sweep
                .setRepeatMode(ValueAnimator.REVERSE)
                  .setAutoStart(true)
                .build();


        mShimmerFrameLayout = mBinding.shMovies;
        mShimmerFrameLayout.setShimmer(shimmer);

    }

    private void startAnimation() {
        mShimmerFrameLayout.setVisibility(View.VISIBLE);
        mShimmerFrameLayout.startShimmer();
        mBinding.rvMovies.setVisibility(View.GONE);
        mBinding.tvMoviesTitle.setVisibility(View.GONE);
        mBinding.avfMoviesDemo.setVisibility(View.GONE);
    }

    private void stopAnimation() {

        mBinding.rvMovies.setVisibility(View.VISIBLE);
        mBinding.avfMoviesDemo.setVisibility(View.VISIBLE);
        mBinding.tvMoviesTitle.setVisibility(View.VISIBLE);
        mShimmerFrameLayout.stopShimmer();

        mShimmerFrameLayout.setVisibility(View.GONE);
    }

    private void setupViewModel() {
        movieViewModel = new ViewModelProvider(requireActivity(), movieViewModelFactory)
                .get(MovieViewModel.class);
    }

    private void setupAdapter() {
        setupDemoAdapter();
        setupMoviesAdapter();
    }


    private void setupDemoAdapter()
    {
        var demoAdapter = new DemoMoviesAdapter(this.getContext());
        demoAdapter.setItemClickListener(this::onItemDemoMovieClick);
        mBinding.setDemoAdapter(demoAdapter);

    }

    private void onItemDemoMovieClick(View view,DemoMovie demoMovie) {
        Timber.e("ItemClick");
        mBinding.getRoot().showSnackbar("itemCLick", Snackbar.LENGTH_SHORT);

        movieViewModel.setDemoMovieSelected(demoMovie);
        Navigation.findNavController(view)
                .navigate(R.id.action_nav_movies_fragment_to_nav_details_fragment);
    }

    private void setupMoviesAdapter() {
        MoviesAdapter moviesAdapter = new MoviesAdapter(this::onItemMovieClick);
        mBinding.setMoviesAdapter(moviesAdapter);

    }

    private void onItemMovieClick(View view,Movie movie) {
        movieViewModel.setMovieSelected(movie);
        Navigation.findNavController(view).navigate(R.id.action_nav_movies_fragment_to_nav_details_fragment);
    }

    private void setupObserver() {
        setupNetworkMonitor();
        setupObserverDemoMovies();
        setupObserverMovies();
    }

    private void setupNetworkMonitor() {
        movieViewModel.getIsOnline().observe(getViewLifecycleOwner(), isOnline -> {
            if (Boolean.TRUE.equals(isOnline)) {
                Timber.e("isOnline = %s", true);
                movieViewModel.fetchMovies();
                movieViewModel.fetchDemoMovies();
            } else {
                Timber.e("isOnline = %s", false);
                mBinding.getRoot().showSnackbar(getString(R.string.all_network_error), BaseTransientBottomBar.LENGTH_SHORT);
            }
        });
    }

    private void setupObserverMovies() {
        movieViewModel.getMoviesLiveData().observe(getViewLifecycleOwner(), moviesResponse -> {
            switch (moviesResponse.status) {
                case LOADING -> {
                    startAnimation();
                    Timber.tag(TAG).d("Movies  LOADING");
                }
                case SUCCESS -> {
                    Timber.tag(TAG).i("Movies Success: %s", moviesResponse);
                    mBinding.setMovieList(moviesResponse.getData().results());
                    mBinding.imvMoviesEmpty.setVisibility(View.GONE);
                    stopAnimation();
                }
                case ERROR -> {
                    mBinding.imvMoviesEmpty.setVisibility(View.VISIBLE);
                    stopAnimation();
                    Timber.tag(TAG).e("Movies  ERROR: %s", moviesResponse.message);
                }
                case NULL -> {
                    Timber.tag(TAG).e("Movies  NULL: %s", moviesResponse.message);
                     mBinding.imvMoviesEmpty.setVisibility(View.VISIBLE);
                    stopAnimation();
                }

            }
        });
    }

    private void setupObserverDemoMovies() {
        movieViewModel.getDemoMoviesLiveData().observe(getViewLifecycleOwner(), demoResponse -> {
            switch (demoResponse.status) {
                case LOADING -> {
                    Timber.tag(TAG).d("Demo movies LOADING");
                    startAnimation();
                }

                case SUCCESS -> {
                    Timber.tag(TAG).d("Demo movies : %s", demoResponse);
                    mBinding.setDemoList(demoResponse.getData().getResults());
                    stopAnimation();

                }
                case ERROR -> {
                    Timber.tag(TAG).e("Demo movies ERROR: %s", demoResponse.message);
                    stopAnimation();
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
            // reloadDataUI();
            mBinding.swipeMovies.setRefreshing(false);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        startAnimation();
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
        mShimmerFrameLayout = null;
        getViewModelStore().clear();
    }
}