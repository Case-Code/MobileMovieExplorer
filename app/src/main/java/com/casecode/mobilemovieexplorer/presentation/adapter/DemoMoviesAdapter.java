package com.casecode.mobilemovieexplorer.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.databinding.ItemDemoMovieBinding;
import com.casecode.mobilemovieexplorer.domain.model.demo.DemoMovie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mahmoud Abdalhafeez on 12/27/2023
 */
public class DemoMoviesAdapter extends BaseAdapter {
    private final ArrayList<DemoMovie> mDemoMovie = new ArrayList<>();

    public DemoMoviesAdapter() {
    }

    public void setDemoMovies(List<DemoMovie> demoMovies) {
        if (demoMovies != null) {
            mDemoMovie.clear();
            mDemoMovie.addAll(demoMovies);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mDemoMovie.size();
    }

    @Override
    public Object getItem(int position) {
        return mDemoMovie.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mDemoMovie.get(position).id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemDemoMovieBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.item_demo_movie, parent, false);
        DemoMovie demo = mDemoMovie.get(position);
        binding.setDemoMovie(demo);
        binding.executePendingBindings();

        return binding.getRoot();
    }

}
