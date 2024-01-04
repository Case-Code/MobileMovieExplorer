package com.casecode.mobilemovieexplorer.presentation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.databinding.FragmentFavoriteMoviesBinding;
import com.casecode.mobilemovieexplorer.domain.model.db.FavoriteMovie;
import com.casecode.mobilemovieexplorer.presentation.adapter.FavoriteAdapter;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.FavoriteViewModel;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.FavoriteViewModelFactory;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModel;
import com.casecode.mobilemovieexplorer.presentation.viewmodel.MovieViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class FavoriteMoviesFragment extends Fragment {
    private static final String TAG = "FavoriteMoviesFragment";
    @Inject
    FavoriteViewModelFactory favoriteViewModelFactory;
    @Inject
    MovieViewModelFactory movieViewModelFactory;

    private FavoriteViewModel favoriteViewModel;
    private MovieViewModel movieViewModel;
    private FragmentFavoriteMoviesBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        setupUi();
    }

    private void setupUi() {
        setupViewModel();
        observerViewModel();
        setupAdapter();
        // Call the method to update the options menu
        updateOptionsMenu();
    }


    private void setupAdapter() {
        var favoriteAdapter = new FavoriteAdapter(this::onItemClick);
        mBinding.setFavoriteAdapter(favoriteAdapter);
    }

    private void onItemClick(View view, FavoriteMovie favoriteMovie) {
        if (favoriteMovie.isDemo) {
            movieViewModel.setDemoMovieIdSelected(favoriteMovie.idMovie);
        } else {
            movieViewModel.setMovieIdSelected(favoriteMovie.idMovie);
        }
        Navigation.findNavController(view)
                .navigate(R.id.action_nav_favoirte_fragment_to_nav_details_fragment);
    }

    private void setupViewModel() {
        favoriteViewModel = new ViewModelProvider(this, favoriteViewModelFactory)
                .get(FavoriteViewModel.class);
        movieViewModel = new ViewModelProvider(requireActivity(), movieViewModelFactory).get(MovieViewModel.class);
    }

    private void observerViewModel() {
        favoriteViewModel.getListFavorite();
        favoriteViewModel.getFavoriteMoviesResource().observe(getViewLifecycleOwner(), favoirteMoviesResource -> {
            switch (favoirteMoviesResource.status) {
                case LOADING -> {
                    Timber.tag(TAG).d("favoirteMoviesResource  LOADING");
                }
                case SUCCESS -> {
                    Timber.tag(TAG).i("favoirteMoviesResource Success: %s", favoirteMoviesResource);
                    var favoriteItems = favoirteMoviesResource.getData();
                    showUiEmptyOrList(favoriteItems);
                }
                case ERROR -> {
                    Timber.tag(TAG).e("favoirteMoviesResource  ERROR: %s", favoirteMoviesResource.message);
                }
                case NULL -> {
                    Timber.tag(TAG).e("favoirteMoviesResource  NULL: %s", favoirteMoviesResource.message);
                }
            }
        });
    }

    private void showUiEmptyOrList(List<FavoriteMovie> favoriteItems) {
        if (favoriteItems.isEmpty()) {
            mBinding.groupFavoriteEmpty.setVisibility(View.VISIBLE);
        } else {
            mBinding.groupFavoriteEmpty.setVisibility(View.GONE);
            mBinding.setFavoriteMovies(favoriteItems);

        }
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