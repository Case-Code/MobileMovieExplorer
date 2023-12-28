package com.casecode.mobilemovieexplorer.presentation.adapter;

import android.content.Context;
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
    private final List<DemoMovie> mDemoMovie = new ArrayList<>();
    private final LayoutInflater inflater;

    private ItemClickListener<DemoMovie> itemClickListener;


    public DemoMoviesAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setItemClickListener(ItemClickListener<DemoMovie> itemClickListener) {
        this.itemClickListener = itemClickListener;


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
    public DemoMovie getItem(int position) {
        return getCount() > 0 ? mDemoMovie.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return mDemoMovie.get(position).id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemDemoMovieBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.item_demo_movie, parent, false);


        DemoMovie demo = mDemoMovie.get(position);
        binding.setDemoMovie(demo);
        binding.setClickListener(itemClickListener);
        binding.executePendingBindings();

       /* binding.imvItemMovie.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(demo);
            }
        });*/
        return binding.getRoot();
    }

}
