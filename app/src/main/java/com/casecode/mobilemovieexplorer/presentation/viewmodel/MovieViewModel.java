package com.casecode.mobilemovieexplorer.presentation.viewmodel;

import static autodispose2.AutoDispose.autoDisposable;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.data.utils.NetworkMonitor;
import com.casecode.mobilemovieexplorer.domain.model.demo.DemoResponse;
import com.casecode.mobilemovieexplorer.domain.model.demodetails.DemoDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.model.movies.Movie;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;
import com.casecode.mobilemovieexplorer.domain.usecase.MovieUseCase;
import com.casecode.mobilemovieexplorer.presentation.utils.Event;
import com.casecode.mobilemovieexplorer.presentation.utils.Resource;

import javax.inject.Inject;

import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;
import kotlinx.coroutines.CoroutineScope;
import lombok.Getter;
import timber.log.Timber;

/**
 * ViewModel class for managing data related to movies and movie details.
 * Utilizes the MovieUseCase for fetching data and NetworkMonitor for monitoring network connectivity.
 * Uses LiveData to observe and notify changes in data to the UI components.
 */
@HiltViewModel
public class MovieViewModel extends ViewModel {

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private final MovieUseCase movieUseCase;
    @Getter
    private final MutableLiveData<Boolean> isOnline = new MutableLiveData<>();
    @Getter
    private final MutableLiveData<Integer> userMessage = new MutableLiveData<>();
    @Getter
    private final MutableLiveData<Resource<MoviesResponse>> moviesLiveData =
            new MutableLiveData<>();
    @Getter
    private final MutableLiveData<Resource<DemoResponse>> demoMoviesLiveData =
            new MutableLiveData<>();
    @Getter
    private final MutableLiveData<Resource<MoviesDetailsResponse>> movieDetailsLiveData =
            new MutableLiveData<>();
    @Getter
    private final MutableLiveData<Resource<DemoDetailsResponse>> demoDetailsLiveData =
            new MutableLiveData<>();
    private final NetworkMonitor networkMonitor;
    @Getter
    private MutableLiveData<PagingData<Movie>> moviesPaging = new MutableLiveData<>();
    @Getter
    private MutableLiveData<Event<Integer>> demoMovieIdSelected = new MutableLiveData<>();
    @Getter
    private MutableLiveData<Event<Integer>> movieIdSelected = new MutableLiveData<>();


    /**
     * Constructs a MovieViewModel with the specified dependencies.
     *
     * @param movieUseCase   The MovieUseCase instance used for fetching movie-related data.
     * @param networkMonitor The NetworkMonitor instance used for monitoring network connectivity.
     */
    @Inject
    public MovieViewModel(MovieUseCase movieUseCase, NetworkMonitor networkMonitor) {
        this.movieUseCase = movieUseCase;
        this.networkMonitor = networkMonitor;
    }


    /**
     * Clears the LiveData holding the user message after it has been shown.
     */
    public void snackbarMessageShown() {
        userMessage.setValue(null);
    }

    /**
     * Shows a Snackbar message with the provided message ID.
     *
     * @param messageId The ID of the message to be shown.
     */
    private void showSnackbarMessage(int messageId) {
        Timber.e("messageId =  %s", messageId);
        userMessage.setValue(messageId);
    }

    /**
     * Sets up a subscription to observe network connectivity changes.
     */
    public void setNetworkMonitor() {
        mCompositeDisposable.add(networkMonitor.isOnline().subscribe(isOnline -> {
                    setConnected(isOnline);
                    showSnackbarMessage(R.string.all_network_error);
                }, Timber::e
        ));
    }

    /**
     * Updates the LiveData to indicate whether the device is currently connected to the internet.
     *
     * @param isConnected True if the device is connected; false otherwise.
     */
    private void setConnected(boolean isConnected) {
        isOnline.postValue(isConnected);
    }

    public void fetchMoviesPaging(LifecycleOwner lifecycleOwner) {
        mCompositeDisposable.add(getMoviesPagingFlowable()
                .to(autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner))).subscribeWith(new DisposableSubscriber<PagingData<Movie>>() {
                    @Override
                    public void onNext(PagingData<Movie> pagingData) {
                    moviesPaging.setValue(pagingData);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        showSnackbarMessage(R.string.movies_loading_error);
                    Timber.e("Error: %s", throwable);
                    }

                    @Override
                    public void onComplete() {
                        Timber.e(" paging data ,onComplete: " );

                    }
                }));

    }

    @NonNull
    private Flowable<PagingData<Movie>> getMoviesPagingFlowable() {
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);

        return PagingRx.cachedIn(movieUseCase.getMoviesPaging(), viewModelScope);


    }

    /**
     * Fetches a list of movies and updates the corresponding LiveData.
     */
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

    /**
     * Fetches a list of demo movies and updates the corresponding LiveData.
     */
    public void fetchDemoMovies() {
        demoMoviesLiveData.setValue(Resource.loading());

        mCompositeDisposable.add(movieUseCase.getDemoMovies()
                .subscribeWith(new DisposableSingleObserver<DemoResponse>() {
                    @Override
                    public void onSuccess(@NonNull DemoResponse demoResponse) {
                        demoMoviesLiveData.setValue(Resource.success(demoResponse));
                        Timber.d("DemoResponse = %s", demoResponse.toString());
                        Timber.d("DemoResponse result = %s", demoResponse.results());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        demoMoviesLiveData.setValue(Resource.error(e.getMessage(), null));
                        Timber.e(e);
                    }
                }));
    }

    public void setMovieIdSelected(int id) {
        movieIdSelected.setValue(new Event<>(id));
    }

    public void setDemoMovieIdSelected(int id) {
        demoMovieIdSelected.setValue(new Event<>(id));
    }



    private void restMovieIdSelected() {
        if (movieIdSelected.getValue() != null)
            movieIdSelected.setValue(null);
    }

    private void restDemoMovieIdSelected() {
        if (demoMovieIdSelected.getValue() != null)

            demoMovieIdSelected.setValue(null);
    }




    /**
     * Fetches details for a specific movie and updates the corresponding LiveData.
     *
     * @param movieId The ID of the movie for which details are to be fetched.
     */
    public void fetchMovieDetails(int movieId) {
        movieDetailsLiveData.setValue(Resource.loading());

        mCompositeDisposable.add(movieUseCase.getMovieDetails(movieId)
                .subscribeWith(new DisposableSingleObserver<MoviesDetailsResponse>() {
                    @Override
                    public void onSuccess(@NonNull MoviesDetailsResponse moviesDetailsResponse) {
                        movieDetailsLiveData.setValue(Resource.success(moviesDetailsResponse));
                        Timber.d("MoviesDetailsResponse = %s",
                                moviesDetailsResponse.toString());
                        restMovieIdSelected();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        movieDetailsLiveData.setValue(Resource.error(e.getMessage(), null));
                        Timber.e(e);
                    }
                }));
    }


    /**
     * Fetches details for a specific demo and updates the corresponding LiveData.
     *
     * @param demoId The ID of the demo for which details are to be fetched.
     */
    public void fetchDemoDetails(int demoId) {
        demoDetailsLiveData.setValue(Resource.loading());

        mCompositeDisposable.add(movieUseCase.getDemoDetails(demoId)
                .subscribeWith(new DisposableSingleObserver<DemoDetailsResponse>() {
                    @Override
                    public void onSuccess(@NonNull DemoDetailsResponse demoDetailsResponse) {
                        demoDetailsLiveData.setValue(Resource.success(demoDetailsResponse));
                        Timber.d("demoDetailsResponse = %s",
                                demoDetailsResponse.toString());
                        restDemoMovieIdSelected();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        demoDetailsLiveData.setValue(Resource.error(e.getMessage(), null));
                        Timber.e(e);
                    }
                }));
    }

    /**
     * Clears resources when the ViewModel is no longer in use.
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.clear();
        mCompositeDisposable.dispose();
    }


}