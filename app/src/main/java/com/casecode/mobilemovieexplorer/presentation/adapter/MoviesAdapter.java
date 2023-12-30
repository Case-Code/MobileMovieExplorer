package com.casecode.mobilemovieexplorer.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.databinding.ItemMovieBinding;
import com.casecode.mobilemovieexplorer.domain.model.movies.Movie;
import com.casecode.mobilemovieexplorer.presentation.base.ItemClickListener;

/**
 * Created by Mahmoud Abdalhafeez on 12/27/2023
 */
public class MoviesAdapter extends ListAdapter<Movie, MoviesAdapter.MovieViewHolder> {

    private final ItemClickListener<Movie> mItemClickListener;

    public MoviesAdapter(ItemClickListener<Movie> itemClickListener) {
        super(new DiffUtil.ItemCallback<>() {
            @Override
            public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
                return oldItem.id() == newItem.id();

            }

            @Override
            public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
                return oldItem.equals(newItem);
            }
        });
        this.mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        ItemMovieBinding binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);

        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        Movie movie = getItem(position);
        movieViewHolder.bind(movie, mItemClickListener);
    }

  public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ItemMovieBinding mBinding;

        public MovieViewHolder(@NonNull ItemMovieBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;

        }

        public void bind(Movie movie, ItemClickListener<Movie> itemClickListener) {
            mBinding.setMovie(movie);
            mBinding.setClickListener(itemClickListener);
            mBinding.executePendingBindings();
        }

    }
}
