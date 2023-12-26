package com.casecode.mobilemovieexplorer.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.casecode.mobilemovieexplorer.data.utils.NetworkMonitor;
import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.usecase.MovieUseCase;
import com.casecode.mobilemovieexplorer.presentation.utils.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import lombok.Getter;
import timber.log.Timber;

@HiltViewModel
public class MovieViewModel extends ViewModel {
    private static final String TAG = "MovieViewModel";
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private final MovieUseCase movieUseCase;
    @Getter
    private final MutableLiveData<Boolean> isOnline = new MutableLiveData<>();
    @Getter
    private final MutableLiveData<Integer> userMessage = new MutableLiveData<>();
    @Getter
    private final MutableLiveData<Resource<MoviesResponse>> moviesLiveData = new MutableLiveData<>();
    @Getter
    private final MutableLiveData<Resource<DemoResponse>> demoMoviesLiveData = new MutableLiveData<>();
    @Getter
    private final MutableLiveData<Resource<MoviesDetailsResponse>> movieDetailsLiveData = new MutableLiveData<>();
    @Getter
    private final MutableLiveData<Resource<DemoDetailsResponse>> demoDetailsLiveData = new MutableLiveData<>();

    private final NetworkMonitor networkMonitor;

    @Inject
    public MovieViewModel(MovieUseCase movieUseCase, NetworkMonitor networkMonitor) {
        this.movieUseCase = movieUseCase;
        this.networkMonitor = networkMonitor;
    }

    public void snackbarMessageShown() {
        userMessage.setValue(null);
    }

    private void showSnakebarMesage(int messageId) {
        Timber.e("messageId =  %s", messageId);
        userMessage.setValue(messageId);
    }

    public void setNetworkMonitor() {
        mCompositeDisposable.add(networkMonitor.isOnline().subscribe(isOnline -> {
                    setConnected(isOnline);

                }, Timber::e
        ));
    }

    private void setConnected(boolean isConnected) {
        isOnline.postValue(isConnected);
    }

    public void fetchMovies() {
        moviesLiveData.postValue(Resource.loading());

        mCompositeDisposable.add(movieUseCase.getMovies()
                .subscribeWith(new DisposableSingleObserver<MoviesResponse>() {
                    @Override
                    public void onSuccess(@NonNull MoviesResponse moviesResponse) {
                        moviesLiveData.setValue(Resource.success(moviesResponse));
                        Timber.d("moviesResponse = %s", moviesResponse.toString());
                        Timber.d("moviesResponse result = %s", moviesResponse.results());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        moviesLiveData.setValue(Resource.error(e.getMessage(), null));
                        Timber.e(e);

                    }
                }));

    }

    public void fetchDemoMovies() {
        demoMoviesLiveData.setValue(Resource.loading());

        mCompositeDisposable.add(movieUseCase.getDemoMovies()
                .subscribeWith(new DisposableSingleObserver<DemoResponse>() {
                    @Override
                    public void onSuccess(@NonNull DemoResponse demoResponse) {
                        demoMoviesLiveData.setValue(Resource.success(demoResponse));
                        Timber.d("DemoResponse = %s", demoResponse.toString());
                        Timber.d("DemoResponse result = %s", demoResponse.getResults());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        demoMoviesLiveData.setValue(Resource.error(e.getMessage(), null));

                        Timber.e(e);

                    }
                }));

    }

    public void fetchMovieDetails(int movieId) {
        movieDetailsLiveData.setValue(Resource.loading());

        mCompositeDisposable.add(movieUseCase.getMovieDetails(movieId)
                .subscribeWith(new DisposableSingleObserver<MoviesDetailsResponse>() {
                    @Override
                    public void onSuccess(@NonNull MoviesDetailsResponse moviesDetailsResponse) {
                        movieDetailsLiveData.setValue(Resource.success(moviesDetailsResponse));
                        Timber.d("MoviesDetailsResponse = %s", moviesDetailsResponse.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        movieDetailsLiveData.setValue(Resource.error(e.getMessage(), null));
                        Timber.e(e);

                    }
                }));

    }

    public void fetchDemoDetails(int demoId) {
        demoDetailsLiveData.setValue(Resource.loading());

        mCompositeDisposable.add(movieUseCase.getDemoDetails(demoId)
                .subscribeWith(new DisposableSingleObserver<DemoDetailsResponse>() {
                    @Override
                    public void onSuccess(@NonNull DemoDetailsResponse demoDetailsResponse) {
                        demoDetailsLiveData.setValue(Resource.success(demoDetailsResponse));
                        Timber.d("demoDetailsResponse = %s", demoDetailsResponse.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        demoDetailsLiveData.setValue(Resource.error(e.getMessage(), null));
                        Timber.e(e);

                    }
                }));

    }


    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.clear();
        mCompositeDisposable.dispose();
    }

}


