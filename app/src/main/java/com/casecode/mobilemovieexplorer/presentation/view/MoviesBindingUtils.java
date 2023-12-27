package com.casecode.mobilemovieexplorer.presentation.view;

import android.graphics.drawable.Drawable;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.casecode.mobilemovieexplorer.domain.model.demo.DemoMovie;
import com.casecode.mobilemovieexplorer.domain.model.movies.Movie;
import com.casecode.mobilemovieexplorer.presentation.adapter.DemoMoviesAdapter;
import com.casecode.mobilemovieexplorer.presentation.adapter.MoviesAdapter;

import java.util.List;

import timber.log.Timber;

/**
 * Created by Mahmoud Abdalhafeez on 12/27/2023
 */
public class MoviesBindingUtils {

    @BindingAdapter("adapterFlipper")
    public static void setAdapterFlipper(AdapterViewFlipper adapterFlipper, DemoMoviesAdapter adapter) {
        adapterFlipper.setAdapter(adapter);
        adapterFlipper.setFlipInterval(4000);
        adapterFlipper.startFlipping();
        adapterFlipper.setHorizontalScrollBarEnabled(true);

    }
    @BindingAdapter("dataFlipper")
    public static void setDataFlipper(AdapterViewFlipper adapterViewFlipper, @Nullable List<DemoMovie> demoList) {
        var adapter = (DemoMoviesAdapter) adapterViewFlipper.getAdapter();
        adapter.setDemoMovies(demoList);
        adapterViewFlipper.setAdapter(adapter);
    }

    @BindingAdapter("setMoviesAdapter")
    public static void setMoviesAdapter(RecyclerView recyclerView,
                                          MoviesAdapter adapter) {
        recyclerView.setAdapter(adapter);

    }
    @BindingAdapter("setDataMoviesAdapter")
    public static void setDataMoviesAdapter(RecyclerView recyclerView, @Nullable List<Movie> list) {
        var adapter = (MoviesAdapter) recyclerView.getAdapter();

        adapter.submitList(list);

    }

    @BindingAdapter(value = {"loadImage", "baseImage"}, requireAll = false)
    public static void loadImage(@NonNull ImageView imageView, String imageURL, Drawable baseImage) {
        if (imageURL == null) {
            imageView.setImageDrawable(baseImage);
        } else {
            try {
                String url;
                if (!imageURL.contains("https://")) {
                    url = "https://" + imageURL; /* URL of Image */

                } else {
                    url = imageURL;
                }
                Glide.with(imageView)
                        .setDefaultRequestOptions(new RequestOptions()
                                .circleCrop())
                        .load(url)
                        .fitCenter()
                        //.placeholder(getShimmerDrawables(imageView.getContext()))
                        //.error(getShimmerDrawables(imageView.getContext()))
                        //.fallback(getShimmerDrawables(imageView.getContext()))
                        .into(imageView);
            } catch (Exception e) {
                Timber.e(e);
            }
        }

    }
}
