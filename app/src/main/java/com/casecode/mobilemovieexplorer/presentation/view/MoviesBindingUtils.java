package com.casecode.mobilemovieexplorer.presentation.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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
import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.domain.model.db.FavoriteMovie;
import com.casecode.mobilemovieexplorer.domain.model.demo.DemoMovie;
import com.casecode.mobilemovieexplorer.paging.LoaderAdapter;
import com.casecode.mobilemovieexplorer.presentation.adapter.DemoMoviesAdapter;
import com.casecode.mobilemovieexplorer.presentation.adapter.FavoriteAdapter;
import com.casecode.mobilemovieexplorer.presentation.adapter.MoviesAdapter;
import com.casecode.mobilemovieexplorer.presentation.adapter.SliderDemoAdapter;
import com.casecode.mobilemovieexplorer.presentation.base.CustomAdapterViewFlipper;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;
import com.smarteist.autoimageslider.SliderView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import timber.log.Timber;

/**
 * Created by Mahmoud Abdalhafeez on 12/27/2023
 */
public class MoviesBindingUtils {
    private static final String BASE_URL_IMAGE = "https://image.tmdb.org/t/p/original/";

    @BindingAdapter("adapterSlideView")
    public static void setAdapterFlipper(SliderView sliderView, SliderDemoAdapter adapter) {
        sliderView.setSliderAnimationDuration(600);
        sliderView.setAutoCycle(true);
        sliderView.setScrollTimeInSec(3);
        sliderView.setSliderAdapter(adapter);

    }

    @BindingAdapter("bindSliderDemo")
    public static void bindSliderDemo(SliderView sliderView, @Nullable List<DemoMovie> demoList) {
        var adapter = (SliderDemoAdapter) sliderView.getSliderAdapter();
        adapter.setDemoMovies(demoList);
        sliderView.setSliderAdapter(adapter);
    }

    @BindingAdapter("setMoviesAdapter")
    public static void setMoviesAdapter(@NonNull RecyclerView recyclerView,
                                        MoviesAdapter adapter) {
        adapter.setStateRestorationPolicy(
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        adapter.withLoadStateHeaderAndFooter(new LoaderAdapter(), new LoaderAdapter());

        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);

    }

    /*@BindingAdapter("moviesPaging")
    public static void bindMoviesPaging(RecyclerView recyclerView, PagingData<Movie> pagingData) {
        if (recyclerView.getAdapter() instanceof MoviesAdapter adapter) {
            adapter.submitData(recyclerView.getLifecycle(),pagingData);
        }
    }*/


    @BindingAdapter("setFavoriteMoviesAdapter")
    public static void setFavoriteMoviesAdapter(@NonNull RecyclerView recyclerView,
                                                FavoriteAdapter adapter) {
        recyclerView.setAdapter(adapter);

    }

    @BindingAdapter("setDataFavoriteMoviesAdapter")
    public static void setDataFavoriteMoviesAdapter(RecyclerView recyclerView, @Nullable List<FavoriteMovie> list) {
        var adapter = (FavoriteAdapter) recyclerView.getAdapter();
        if (adapter == null) return;

        if (list != null) {
            adapter.updateList(list);

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
                        /*.setDefaultRequestOptions(new RequestOptions()
                                .autoClone())*/
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache both original and resized image

                        .placeholder(getShimmerDrawables(imageView.getContext()))
                        .error(baseImage)
                       // .fallback(getShimmerDrawables(imageView.getContext()))
                        .into(imageView);
            } catch (Exception e) {
                Timber.e(e);
                imageView.setImageDrawable(baseImage);

            }
        }

    }


    private static ShimmerDrawable getShimmerDrawables(Context context) {

        Shimmer shimmer = new Shimmer.ColorHighlightBuilder()
                .setBaseColor(ContextCompat.getColor(context, R.color.md_theme_inversePrimary))
                .setDuration(2000L) // how long the shimmering animation takes to do one full sweep
                .setBaseAlpha(0.6f) //the alpha of the underlying children
                .setHighlightAlpha(0.7f) // the shimmer alpha amount
                .setHighlightColor(ContextCompat.getColor(context, R.color.md_theme_primaryContainer))

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
