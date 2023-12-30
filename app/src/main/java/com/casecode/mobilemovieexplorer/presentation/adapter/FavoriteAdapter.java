package com.casecode.mobilemovieexplorer.presentation.adapter;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.databinding.ItemFavoriteBinding;
import com.casecode.mobilemovieexplorer.domain.model.db.FavoriteMovie;
import com.casecode.mobilemovieexplorer.presentation.base.ItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<FavoriteMovie> favoriteList = new ArrayList<>();
    private final ItemClickListener<FavoriteMovie> itemClickListener;

    public FavoriteAdapter(ItemClickListener<FavoriteMovie> favoriteMovieItemClickListener) {
        itemClickListener = favoriteMovieItemClickListener;
    }


    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<FavoriteMovie> favoriteList){
        this.favoriteList.clear();
        this.favoriteList.addAll(favoriteList);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use ViewBinding to inflate the layout
        ItemFavoriteBinding binding = ItemFavoriteBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavoriteMovie favoriteItem = favoriteList.get(position);

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

        public void bind(FavoriteMovie favoriteItem) {
            binding.setClickListener(itemClickListener);
            binding.setFavoriteMovie(favoriteItem);

            // Use ViewBinding to access views
            // Assuming 'binding' is the ViewBinding instance for your layout
            // Example of setting an image using Glide with a ProgressBar
          //  bindImage(favoriteItem);
            // Set data to other views
         //   binding.textOriginalTitle.setText(favoriteItem.getOriginalTitle());
           // binding.textVoteAverage.setText(String.valueOf(favoriteItem.getVoteAverage()));
            // Example of setting text to a TextView with formatted string using View Binding
            //String runtimeFormat = binding.textRuntime.getContext().getString(R.string.runtime_format);
            //String runtime = String.valueOf(favoriteItem.getRuntime());
            //binding.textRuntime.setText(String.format(runtimeFormat, runtime));

            // Set onClickListener for likeButton if needed
            // binding.imageButtonLike.setOnClickListener(v -> handleLikeButtonClick(favoriteItem));
        }

        private void bindImage(FavoriteMovie favoriteItem) {
            Glide.with(binding.imageBackdropPath.getContext())
                    .load(favoriteItem.getBackdropPath())
                    .placeholder(R.drawable.panorama_24) // Placeholder image while loading
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            // Hide progress bar on failure
                          //  binding.progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            // Hide progress bar on success
                           // binding.progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(binding.imageBackdropPath);
        }
    }
}
