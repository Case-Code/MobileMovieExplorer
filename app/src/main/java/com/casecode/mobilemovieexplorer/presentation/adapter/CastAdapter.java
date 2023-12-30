package com.casecode.mobilemovieexplorer.presentation.adapter;

import android.annotation.SuppressLint;
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
import com.casecode.mobilemovieexplorer.databinding.ItemCastBinding;
import com.casecode.mobilemovieexplorer.domain.model.moviesdetails.Cast;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

    private static final String PROFILE_PATH_BASE_URL = "https://image.tmdb.org/t/p/original/";

    private List<Cast> castList = new ArrayList<>();

    public CastAdapter() {
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<Cast> castList) {
        this.castList = castList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        ItemCastBinding itemCastBinding = ItemCastBinding.inflate(layoutInflater, parent, false);
        return new CastViewHolder(itemCastBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        Cast cast = castList.get(position);

       holder.bind( cast);
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    public static class CastViewHolder extends RecyclerView.ViewHolder {

        private ItemCastBinding binding;
        public CastViewHolder(@NonNull ItemCastBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
        private void bind( Cast cast) {
            binding.setCast(cast);
            binding.executePendingBindings();
        }

    }
}
