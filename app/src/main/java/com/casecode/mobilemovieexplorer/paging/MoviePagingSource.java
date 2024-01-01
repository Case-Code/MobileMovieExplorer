package com.casecode.mobilemovieexplorer.paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.casecode.mobilemovieexplorer.data.source.MoviesRemoteDataSource;
import com.casecode.mobilemovieexplorer.domain.model.movies.Movie;
import com.casecode.mobilemovieexplorer.domain.model.movies.MoviesResponse;
import com.casecode.mobilemovieexplorer.presentation.utils.Resource;
import com.casecode.mobilemovieexplorer.presentation.utils.Status;

import io.reactivex.rxjava3.core.Single;

/**
 * Created by Mahmoud Abdalhafeez on 12/31/2023
 */
public class MoviePagingSource extends RxPagingSource<Integer, Movie> {

    private final MoviesRemoteDataSource mRemoteDataSource;

    public MoviePagingSource(MoviesRemoteDataSource remoteDataSource) {
        this.mRemoteDataSource = remoteDataSource;
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Movie> state) {
        // Try to find the page key of the closest page to anchorPosition from
        // either the prevKey or the nextKey; you need to handle nullability
        // here.
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey are null -> anchorPage is the
        //    initial page, so return null.

        Integer anchorPosition = state.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }
        LoadResult.Page<Integer, Movie> anchorPage = state.closestPageToPosition(anchorPosition);
        if (anchorPage == null) {
            return null;
        }

        Integer prevKey = anchorPage.getPrevKey();
        if (prevKey != null) {
            return prevKey + 1;
        }
        Integer nextKey = anchorPage.getNextKey();
        if (nextKey != null) {
            return nextKey - 1;
        }

        return null;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, Movie>> loadSingle(@NonNull LoadParams<Integer> loadParams) {

        int page = loadParams.getKey() != null ? loadParams.getKey() : 1;

        return mRemoteDataSource.getMovies(page).map(resource -> {
            if (resource != null && resource.getStatus() == Status.SUCCESS && resource.getData() != null) {
                Integer nextPage = resource.getData().page() + 1;
                return new LoadResult.Page<>(resource.getData().results(),
                        null, nextPage,
                        LoadResult.Page.COUNT_UNDEFINED,
                        LoadResult.Page.COUNT_UNDEFINED);
            } else {
                return new LoadResult.Error<>(new Throwable("Failed to load data"));
            }
        });

    }

    @Override
    public boolean getKeyReuseSupported() {
        return true;
    }

    private LoadResult toLoadResult(Resource<MoviesResponse> moviesResponseResource) {
        return new LoadResult.Page<>(moviesResponseResource.getData().results(),
                null, moviesResponseResource.getData().page() + 1,
                LoadResult.Page.COUNT_UNDEFINED,
                LoadResult.Page.COUNT_UNDEFINED);
    }


}
