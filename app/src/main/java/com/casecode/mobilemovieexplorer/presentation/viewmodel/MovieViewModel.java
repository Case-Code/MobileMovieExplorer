package com.casecode.mobilemovieexplorer.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.usecase.MovieUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

@HiltViewModel
public class MovieViewModel extends ViewModel {
    private static final String TAG = "MovieViewModel";
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private final MovieUseCase movieUseCase;

    private final MutableLiveData<MoviesResponse> moviesLiveData = new MutableLiveData<>();
    private final MutableLiveData<DemoResponse> demoMoviesLiveData = new MutableLiveData<>();
    private final MutableLiveData<MoviesDetailsResponse> movieDetailsLiveData = new MutableLiveData<>();
    private final MutableLiveData<DemoDetailsResponse> demoDetailsLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    @Inject
    public MovieViewModel(MovieUseCase movieUseCase) {
        this.movieUseCase = movieUseCase;
    }

    public void fetchMovies() {
        mCompositeDisposable.add(movieUseCase.getMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<MoviesResponse>() {
                    @Override
                    public void onSuccess(@NonNull MoviesResponse moviesResponse) {
                        moviesLiveData.setValue(moviesResponse);
                        Timber.d("moviesResponse = %s", moviesResponse.toString());
                        Timber.d("moviesResponse result = %s", moviesResponse.getResults());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        errorLiveData.setValue("moviesResponse,Failed to fetch movies");
                        Timber.e(e);

                    }
                }));

    }

    public void fetchDemoMovies() {
        mCompositeDisposable.add(movieUseCase.getDemoMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<DemoResponse>() {
                    @Override
                    public void onSuccess(@NonNull DemoResponse demoResponse) {
                        demoMoviesLiveData.setValue(demoResponse);
                        Timber.d("DemoResponse = %s", demoResponse.toString());
                        Timber.d("DemoResponse result = %s", demoResponse.getResults());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        errorLiveData.setValue("DemoResponse,Failed to fetch movies");
                        Timber.e(e);

                    }
                }));

    }

    public void fetchMovieDetails(int movieId) {
        mCompositeDisposable.add(movieUseCase.getMovieDetails(movieId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<MoviesDetailsResponse>() {
                    @Override
                    public void onSuccess(@NonNull MoviesDetailsResponse moviesDetailsResponse) {
                        movieDetailsLiveData.setValue(moviesDetailsResponse);
                        Timber.d("MoviesDetailsResponse = %s", moviesDetailsResponse.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        errorLiveData.setValue("MoviesDetailsResponse, Failed to fetch movies");
                        Timber.e(e);

                    }
                }));

    }

    public void fetchDemoDetails(int demoId) {
        mCompositeDisposable.add(movieUseCase.getDemoDetails(demoId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<DemoDetailsResponse>() {
                    @Override
                    public void onSuccess(@NonNull DemoDetailsResponse demoDetailsResponse) {
                        demoDetailsLiveData.setValue(demoDetailsResponse);
                        Timber.d("demoDetailsResponse = %s", demoDetailsResponse.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        errorLiveData.setValue("demoDetailsResponse, Failed to fetch movies");
                        Timber.e(e);

                    }
                }));

    }

    public LiveData<MoviesResponse> getMoviesLiveData() {
        return moviesLiveData;
    }

    public LiveData<DemoResponse> getDemoMoviesLiveData() {
        return demoMoviesLiveData;
    }

    public LiveData<MoviesDetailsResponse> getMovieDetailsLiveData() {
        return movieDetailsLiveData;
    }

    public LiveData<DemoDetailsResponse> getDemoDetailsLiveData() {
        return demoDetailsLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.clear();
        mCompositeDisposable.dispose();
    }

}


