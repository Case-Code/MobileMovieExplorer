package com.casecode.mobilemovieexplorer.presentation.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.Cast;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {

    public static final String PROFILE_PATH_BASE_URL = "https://image.tmdb.org/t/p/original/";

    private List<Cast> castList;
    private Context context;

    public CastAdapter(List<Cast> castList) {
        this.castList = castList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_cast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cast cast = castList.get(position);

        String profilePath = cast.getProfilePath();

        // Show progress bar while loading
        holder.progressBar.setVisibility(View.VISIBLE);

        if (profilePath != null) {
            // Load cast images using Glide or Picasso
            Glide.with(context)
                    .load(PROFILE_PATH_BASE_URL.concat(profilePath))
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(
                                @Nullable GlideException e,
                                Object model, Target<Drawable> target,
                                boolean isFirstResource) {
                            // Hide progress bar on failure
                            holder.progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(
                                Drawable resource,
                                Object model,
                                Target<Drawable> target,
                                DataSource dataSource,
                                boolean isFirstResource) {
                            // Hide progress bar on success
                            holder.progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(holder.castImageView);
        } else {
            // Hide progress bar if profilePath is null
            holder.progressBar.setVisibility(View.GONE);

            // Handle the case where profilePath is null
            // You can set a default image or take other appropriate action
            Glide.with(context)
                    .load(R.drawable.account_circle_24)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.castImageView);
        }

        holder.textViewName.setText(cast.getName());
    }


    @Override
    public int getItemCount() {
        return castList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView castImageView;
        TextView textViewName;
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            castImageView = itemView.findViewById(R.id.imageViewProfile);
            textViewName = itemView.findViewById(R.id.textViewName);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
