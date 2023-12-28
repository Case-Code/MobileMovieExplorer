package com.casecode.mobilemovieexplorer.presentation.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.domain.model.demo.DemoMovie;
import com.casecode.mobilemovieexplorer.domain.model.movies.Movie;
import com.casecode.mobilemovieexplorer.presentation.adapter.DemoMoviesAdapter;
import com.casecode.mobilemovieexplorer.presentation.adapter.MoviesAdapter;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import timber.log.Timber;

/**
 * Created by Mahmoud Abdalhafeez on 12/27/2023
 */
public class MoviesBindingUtils {
    private final static String BASE_URL_IMAGE = "https://image.tmdb.org/t/p/original/";

    @BindingAdapter({"bindImage"})
    public static void setVisibility(@NotNull ImageView imageView, int size) {
        imageView.setVisibility(size == 0 ? View.VISIBLE : View.GONE);
    }

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
        if (list != null && adapter != null) {
            adapter.submitList(list);

        } else {
            Timber.e("adapter = " + adapter + ", List = " + list);
        }

    }

    @BindingAdapter(value = {"loadImage", "baseImage"}, requireAll = false)
    public static void loadImage(@NonNull ImageView imageView, String imageURL, Drawable baseImage) {
        Timber.e("url of image = %s", imageURL);
        if (imageURL == null) {
            imageView.setImageDrawable(baseImage);
        } else {
            try {
                String url;
                if (!imageURL.contains("https://")) {
                    url = BASE_URL_IMAGE + imageURL; /* URL of Image */

                } else {
                    url = imageURL;
                }


                Glide.with(imageView.getContext())
                           .setDefaultRequestOptions(new RequestOptions()
                                   .autoClone())
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache both original and resized image

                        /*.placeholder(getShimmerDrawables(imageView.getContext()))
                        .error(getShimmerDrawables(imageView.getContext()))
                        .fallback(getShimmerDrawables(imageView.getContext()))*/
                        .into(imageView);
            } catch (Exception e) {
                Timber.e(e);
                imageView.setImageDrawable(baseImage);

            }
        }

    }


    private static ShimmerDrawable getShimmerDrawables(Context context) {

        Shimmer shimmer = new Shimmer.ColorHighlightBuilder()
                .setBaseColor(ContextCompat.getColor(context, R.color.md_theme_primaryContainer))
                .setDuration(1000L) // how long the shimmering animation takes to do one full sweep
                .setBaseAlpha(0.6f) //the alpha of the underlying children
                .setHighlightAlpha(0.7f) // the shimmer alpha amount
                .setHighlightColor(ContextCompat.getColor(context, R.color.md_theme_onPrimaryContainer))

                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build();

        ShimmerDrawable shimmerDrawables = new ShimmerDrawable();
        shimmerDrawables.setShimmer(shimmer);
        return shimmerDrawables;
    }

    @BindingAdapter({"isAvailable"})
    public static void setTextWithNetworkAvailable(@NotNull TextView textView, boolean isAvailable) {
        textView.setBackgroundColor(ContextCompat.getColor(textView.getContext(),
                isAvailable ? R.color.md_theme_primaryContainer : R.color.md_theme_onSurface));
        textView.setTextColor(ContextCompat.getColor(textView.getContext(),
                isAvailable ? R.color.md_theme_onPrimaryContainer : R.color.md_theme_surface));
        View rootView = textView.getRootView();

        ConstraintLayout viewGroup = rootView != null ? (ConstraintLayout) rootView.findViewById(R.id.csl_main) : null;
        if (viewGroup != null) {
            slideAnimation(textView, viewGroup);
        }

        textView.setVisibility(!isAvailable ? View.VISIBLE : View.GONE);
    }

    private static void slideAnimation(TextView textView, ViewGroup root) {
        Transition transition = (new Slide(Gravity.BOTTOM));
        transition.setDuration(2000L);
        transition.addTarget(textView);
        TransitionManager.beginDelayedTransition(root, transition);
    }
}
