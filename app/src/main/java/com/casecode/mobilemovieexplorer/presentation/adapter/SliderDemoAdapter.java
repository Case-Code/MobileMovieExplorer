package com.casecode.mobilemovieexplorer.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.casecode.mobilemovieexplorer.databinding.ItemDemoMovieBinding;
import com.casecode.mobilemovieexplorer.domain.model.demo.DemoMovie;
import com.casecode.mobilemovieexplorer.presentation.base.ItemClickListener;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;

/**
 * Created by Mahmoud Abdalhafeez on 1/2/2024
 */
public class SliderDemoAdapter extends SliderViewAdapter<SliderDemoAdapter.SliderViewHolder> {
    private final List<DemoMovie> mDemoMovie = new ArrayList<>();

    @Setter
    private ItemClickListener<DemoMovie> itemClickListener;

    public void setDemoMovies(List<DemoMovie> demoMovies) {
        if (demoMovies != null) {
            mDemoMovie.clear();
            mDemoMovie.addAll(demoMovies);
            notifyDataSetChanged();
        }
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {

        return SliderViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder sliderViewHolder, int i) {
        DemoMovie demoMovie = mDemoMovie.get(i);
        sliderViewHolder.bind(demoMovie, itemClickListener);
    }

    @Override
    public int getCount() {
        return mDemoMovie.size();
    }

   public static class SliderViewHolder extends SliderViewAdapter.ViewHolder {
        private  ItemDemoMovieBinding mBinding;

        public SliderViewHolder(ItemDemoMovieBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        static SliderViewHolder from(ViewGroup parent) {
            ItemDemoMovieBinding binding = ItemDemoMovieBinding.inflate(
                    LayoutInflater.from(parent.getContext()), parent, false);
            return new SliderViewHolder(binding);
        }

        public void bind(DemoMovie demoMovie, ItemClickListener<DemoMovie> listener) {

            mBinding.setDemoMovie(demoMovie);
            mBinding.setClickListener(listener);
            mBinding.executePendingBindings();
        }
    }
}
