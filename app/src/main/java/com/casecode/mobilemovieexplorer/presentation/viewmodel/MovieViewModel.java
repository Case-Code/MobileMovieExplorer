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
                        Timber.d("value = %s", moviesResponse.toString());
                        Timber.d("value result = %s", moviesResponse.getResults());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        errorLiveData.setValue("Failed to fetch movies");
                        Timber.e(e);

                    }
                }));

    }

  /*  public void fetchDemoMovies() {
        Call<DemoResponse> call = movieRepository.getDemoMovies();
        call.enqueue(new Callback<DemoResponse>() {
            @Override
            public void onResponse(Call<DemoResponse> call, Response<DemoResponse> response) {
                if (response.isSuccessful()) {
                    demoMoviesLiveData.setValue(response.body());
                } else {
                    errorLiveData.setValue("Failed to fetch demo movies");
                }
            }

            @Override
            public void onFailure(Call<DemoResponse> call, Throwable t) {
                errorLiveData.setValue("Network error");
            }
        });
    }

    public void fetchMovieDetails(int movieId) {
        Call<MoviesDetailsResponse> call = movieRepository.getMovieDetails(movieId);
        call.enqueue(new Callback<MoviesDetailsResponse>() {
            @Override
            public void onResponse(Call<MoviesDetailsResponse> call, Response<MoviesDetailsResponse> response) {
                if (response.isSuccessful()) {
                    movieDetailsLiveData.setValue(response.body());
                } else {
                    errorLiveData.setValue("Failed to fetch movie details");
                }
            }

            @Override
            public void onFailure(Call<MoviesDetailsResponse> call, Throwable t) {
                errorLiveData.setValue("Network error");
            }
        });
    }

    public void fetchDemoDetails(int demoId) {
        Call<DemoDetailsResponse> call = movieRepository.getDemoDetails(demoId);
        call.enqueue(new Callback<DemoDetailsResponse>() {
            @Override
            public void onResponse(Call<DemoDetailsResponse> call, Response<DemoDetailsResponse> response) {
                if (response.isSuccessful()) {
                    demoDetailsLiveData.setValue(response.body());
                } else {
                    errorLiveData.setValue("Failed to fetch demo details");
                }
            }

            @Override
            public void onFailure(Call<DemoDetailsResponse> call, Throwable t) {
                errorLiveData.setValue("Network error");
            }
        });
    }*/

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


