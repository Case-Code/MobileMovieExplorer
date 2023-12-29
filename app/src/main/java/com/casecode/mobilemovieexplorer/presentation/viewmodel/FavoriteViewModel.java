package com.casecode.mobilemovieexplorer.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.casecode.mobilemovieexplorer.data.repository.FavoriteMoviesRepository;
import com.casecode.mobilemovieexplorer.domain.model.db.FavoriteMovie;
import com.casecode.mobilemovieexplorer.presentation.utils.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import lombok.Getter;

@HiltViewModel
public class FavoriteViewModel extends ViewModel {
    private final FavoriteMoviesRepository favoriteMoviesRepository;
    private final CompositeDisposable compositeDisposable;
    @Getter
    private MutableLiveData<Resource<List<FavoriteMovie>>> favoriteMoviesResource = new MutableLiveData<>();
    @Getter
    private MutableLiveData<Boolean> isAddingFavoriteMovie = new MutableLiveData<>();
    @Getter
    private MutableLiveData<Boolean> isDeletingFavoriteMovie = new MutableLiveData<>();

    @Inject
    public FavoriteViewModel(FavoriteMoviesRepository favoriteMoviesRepository) {
        this.favoriteMoviesRepository = favoriteMoviesRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    public void addFavoriteMovie(FavoriteMovie favoriteMovie) {

        compositeDisposable.add(favoriteMoviesRepository.addFavoriteMovie(favoriteMovie).subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                isAddingFavoriteMovie.setValue(true);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                isAddingFavoriteMovie.setValue(false);
            }
        }));
    }

    public void getListFavorite() {
        favoriteMoviesResource.setValue(Resource.loading());

        compositeDisposable.add(favoriteMoviesRepository.getListFavorite()
                .subscribeWith(new DisposableSingleObserver<List<FavoriteMovie>>() {
                    @Override
                    public void onSuccess(@NonNull List<FavoriteMovie> favoriteMovies) {
                        if (favoriteMovies.isEmpty()) {
                            favoriteMoviesResource.setValue(Resource.empty(null));
                        } else {
                            favoriteMoviesResource.setValue(Resource.success(favoriteMovies));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        favoriteMoviesResource.setValue(Resource.error(e.getMessage(), null));

                    }
                }));
    }

    public void deleteFavoriteMovie(FavoriteMovie moviesDetailsMovie) {

         compositeDisposable.add(favoriteMoviesRepository.deleteFavoriteMovie(moviesDetailsMovie).subscribeWith(new DisposableCompletableObserver() {
             @Override
             public void onComplete() {
                 isDeletingFavoriteMovie.setValue(true);

             }

             @Override
             public void onError(@NonNull Throwable e) {
                 isDeletingFavoriteMovie.setValue(false);

             }
         }));



    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }


}

