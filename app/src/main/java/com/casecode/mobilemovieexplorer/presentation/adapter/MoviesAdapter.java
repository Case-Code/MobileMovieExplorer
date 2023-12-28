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

/**
 * Created by Mahmoud Abdalhafeez on 12/27/2023
 */
public class MoviesAdapter extends ListAdapter<Movie, MoviesAdapter.MovieViewHolder> {

    private final ItemClickListener<Movie> mItemClickListener;

    public MoviesAdapter(ItemClickListener<Movie> itemClickListener) {
        super(new DiffUtil.ItemCallback<Movie>() {
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
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMovieBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_movie, parent, false);

        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        Movie movie = getItem(position);
        movieViewHolder.bind(movie, mItemClickListener);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieBinding mBinding;

        public MovieViewHolder(@NonNull ItemMovieBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
          /*  itemView.setOnClickListener(v -> {
                int position = getBindingAdapterPosition();
                if(position != RecyclerView.NO_POSITION)
                {
                    mItemClickListener.onItemClick(getItem(position));
                }
            });*/
        }

        public void bind(Movie movie, ItemClickListener<Movie> itemClickListener) {
            mBinding.setMovie(movie);
            mBinding.setClickListener(itemClickListener);
            mBinding.executePendingBindings();
        }

    }
}
