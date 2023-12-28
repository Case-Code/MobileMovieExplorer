package com.casecode.mobilemovieexplorer.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.databinding.ItemFavoriteBinding;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.MoviesDetailsResponse;

import java.util.List;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<MoviesDetailsResponse> favoriteList;
    private Context context;

    public FavoriteAdapter(Context context, List<MoviesDetailsResponse> favoriteList) {
        this.context = context;
        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use ViewBinding to inflate the layout
        ItemFavoriteBinding binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MoviesDetailsResponse favoriteItem = favoriteList.get(position);

        // Bind data to views using ViewBinding
        holder.bind(favoriteItem);
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemFavoriteBinding binding;

        public ViewHolder(ItemFavoriteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MoviesDetailsResponse favoriteItem) {
            // Use ViewBinding to access views
            // Load image using Glide
            Glide.with(context)
                    .load(favoriteItem.getBackdropPath())
                    .placeholder(R.drawable.panorama_24)
                    .into(binding.imageBackdropPath);

            // Set visibility of views based on loading state
            if (favoriteItem.isLoading()) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.imageButtonLike.setVisibility(View.INVISIBLE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
                binding.imageButtonLike.setVisibility(View.VISIBLE);
            }

            // Set data to other views
            binding.textOriginalTitle.setText(favoriteItem.getOriginalTitle());
            binding.textVoteAverage.setText(String.valueOf(favoriteItem.getVoteAverage()));
            binding.textRuntime.setText(context.getString(R.string.runtime_format, favoriteItem.getRuntime()));

            // Set onClickListener for likeButton if needed
            // binding.imageButtonLike.setOnClickListener(v -> handleLikeButtonClick(favoriteItem));
        }
    }
}
